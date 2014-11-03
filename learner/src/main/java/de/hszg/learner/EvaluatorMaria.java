package de.hszg.learner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.TDistribution;
import org.apache.commons.math.distribution.TDistributionImpl;
import org.apache.commons.math.exception.MathIllegalArgumentException;
import org.apache.commons.math.stat.descriptive.SummaryStatistics;

import de.hszg.learner.featureVector.FeatureVector;





public class EvaluatorMaria {
	/** the percentage (between 0 und 100) of vectors from the data to be used for the test
	*/
	private static int testRate = 40; 
	private ArrayList<Double> means = new ArrayList<>();
	
	public EvaluatorMaria(String filename) {
		List<FeatureVector> vectors = readData(filename); 
		
		//Learner learner = new DummyLearner();
		Learner learner = new SingleLayerArtificialNeuronalNetwork(vectors.get(0).getNumFeatures());
		
		int i=0;
		// TODO: folgendes muss zur Evaluierung mehrfach ausgefuehrt werden
		// Verschiedene Teilmengen finden und Verschiedene Reihenfolgen festlegen,
		// wie oft, das haengt vom gewuenschten Vertrauensintervall ab
		do{
			vectors = mixData(vectors);
			List<List<FeatureVector>> sets = extractTrainingData(vectors);
			learner.learn(sets.get(0));
			Vector<Integer> result = evaluate(sets.get(1),learner);
			evalResult(result);
			i++;
		}while(i<101); //TODO: eine andere Abbruchbedingung verwenden
		
		double sum =0; 
		for (Double d : means)
		{
			sum+=d;
		}
		System.out.println("Im Durchnitt "+ (sum/means.size())+"% Richtig");
		
		SummaryStatistics stats = new SummaryStatistics();
	        for (double val : means) {
	            stats.addValue(val);
	        }
	 
	        // Calculate 95% confidence interval
	        double ci = calcMeanCI(stats, 0.95);
	        System.out.println(String.format("Mean: %f", stats.getMean()));
	        double lower = stats.getMean() - ci;
	        double upper = stats.getMean() + ci;
	        System.out.println(String.format("Confidence Interval 95%%: %f, %f", lower, upper));
	    
	}

private static double calcMeanCI(SummaryStatistics stats, double level) {
    try {
        // Create T Distribution with N-1 degrees of freedom
        TDistribution tDist = new TDistributionImpl(stats.getN() - 1);
        // Calculate critical value
        double critVal = tDist.inverseCumulativeProbability(1.0 - (1 - level) / 2);
        // Calculate confidence interval
        return critVal * stats.getStandardDeviation() / Math.sqrt(stats.getN());
    } catch (MathIllegalArgumentException | MathException e) {
        return Double.NaN;
    }
}
	/**
	 * Evaluate the reulst from the test for output or furthjer considerations
	 * @param result a Vector containing 3 values: a) right classification ba used learner, 
	 * b) lerner could not decide or c) learner found wrong concept
	 */
	private void evalResult(Vector<Integer> result) {
		// TODO hier muss mehr Auswertung passieren, insbes: Vertrauensintervalle etc
		
		float sum=result.get(0)+result.get(1)+result.get(2); //sume
		means.add((double) ((result.get(0)/sum)*100));//habe ein Liste wo der richten sind (%)
		
		
		
		System.out.println("Learning result: \n correct: "+result.get(0)+"\n unknown: "+result.get(1)+"\n wrong: "+result.get(2));
	}
	/** evaluate the learner with a given test set. 
	 * 
	 * @param list: The set of test examples containing the correct concept
	 * @param learner: The learner to be tests
	 * 
	 * @return a vector containing the test results: success, unknown, false
	 * 
	 */
private Vector<Integer> evaluate(List<FeatureVector> list, Learner learner) {
		int success=0;int unknown=0;int fault =0;
		for(FeatureVector fv : list){
			Concept c = learner.classify(fv);
			if(c.equals(Concept.Unknown)) unknown++;
			else if(c.equals(fv.getConcept())) success++;
			else fault++;
				
		}
		Vector<Integer> res = new Vector<>();
		res.add(0,success);
		res.add(1,unknown);
		res.add(2,fault);
		return res;
	}
/**
 * 
 * @param vectors a list of vectors
 * @return list containing the same vectors as parameter but 
 * (usually) in different order
 */
	private List<FeatureVector> mixData(List<FeatureVector> vectors) {
		// TODO: die Reihenfolge der Elemente zufaellig veraendern
		
		long seed = System.nanoTime();
		Collections.shuffle(vectors, new Random(seed));
		return vectors;
	}

	/**
	 * Split the set of feature vectors in a set of traing data and a set of test data.
	 * For representative results it is essential to mix the order of vectors 
	 * before splitting the set
	 * 
	 * @param vectors :a List fo Feature Vectors we can use for the test
	 * @return a List containt two Lists: first the training data, second the test data they are disjunct subsets of vector 
	 *
	 */
	private List<List<FeatureVector>> extractTrainingData(
			List<FeatureVector> vectors) {
		List<List<FeatureVector>> result = new LinkedList<>();
		List<FeatureVector> trainingData = new LinkedList<>();
		List<FeatureVector> testData = new LinkedList<>();
		
		int cut = (int) ((testRate/100f) * vectors.size());
		trainingData.addAll(vectors.subList(0,cut));
		testData.addAll(vectors.subList(cut+1, vectors.size()));
		
		result.add(trainingData);
		result.add(testData);
		return result;
	}

	/** read data from file
	 * 
	 * @param filename the file with this name should contain a serialized List<FeatureVector> containt all the data
	 * @return all the data
	 */
	private List<FeatureVector> readData(String filename) {
		List<FeatureVector> vectors = null;
		try{
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream( new FileInputStream(filename)));
			vectors = (List<FeatureVector>) in.readObject();
			in.close();
		}catch(Throwable t){
			System.out.println("Could not read Data from file: "+filename);
			System.exit(1);
		}
		return vectors;
	}

	/** run the program with training set provided in file with 
	 * name given in first parameter
	 * @param args 1. filename of Serialized List<FeatureVector>
	 */
	public static void main(String[] args){
		
		
		URL url = EvaluatorMaria.class.getResource("/zdata.dat");
		
		File file = null;
		
		try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
		
		/*String filename=null;
		if(args.length==0){
			System.out.println("No data file provided, using dummy data: DummyData.dat");
			filename = "DummyData.dat";
		}
		else 
			filename = args[0];*/
		new EvaluatorMaria(file.getAbsolutePath());
		
			
	}
}






















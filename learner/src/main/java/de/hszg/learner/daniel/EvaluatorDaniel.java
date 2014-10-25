package de.hszg.learner.daniel;

import de.hszg.learner.Concept;
import de.hszg.learner.Learner;
import de.hszg.learner.SingleLayerArtificialNeuronalNetwork;
import de.hszg.learner.featureVector.FeatureVector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class EvaluatorDaniel {
	/** the percentage (between 0 und 100) of vectors from the data to be used for the test
	*/
	private static int testRate = 40;


	public EvaluatorDaniel(String filename) {
        int i = 0;
        int numberOfTests = 100000;

		List<FeatureVector> vectors = readData(filename);
		
		Learner learner = new SingleLayerArtificialNeuronalNetwork(vectors.get(0).getNumFeatures());
		
		float success = 0;
        float unknown = 0;
        float wrong = 0;


		// TODO: folgendes muss zur Evaluierung mehrfach ausgef�hrt werden
		// Verschiedene Teilmengen finden und Verschiedene Reihenfolgen festlegen,
		// wie oft, das h�ngt vom gew�nschten Vertrauensintervall ab
		do{
            learner = new SingleLayerArtificialNeuronalNetwork(vectors.get(0).getNumFeatures()); //if the initialization isn't made each time the evaluation is the same every time, hoping that this is a problem which only occurs since we haven't enough data

			vectors = mixData(vectors);
			List<List<FeatureVector>> sets = extractTrainingData(vectors);
			learner.learn(sets.get(0));
			Vector<Integer> result = evaluate(sets.get(1),learner);

            success += result.get(0) / (float) sets.get(1).size();
            unknown += result.get(1) / (float) sets.get(1).size();
            wrong += result.get(2) / (float) sets.get(1).size();

			evalResult(result);

            i++;
		}while(i < numberOfTests);

        System.out.println("Result after " + numberOfTests + " Test with " + vectors.size() + " FeatureVectors:");
        System.out.println("Learning result: \n correct: " + (success / numberOfTests) * 100f +"%\n unknown: " + (unknown / numberOfTests) * 100f + "%\n wrong: "+(wrong / numberOfTests) * 100f + "%");
	}
	/**
	 * Evaluate the reulst from the test for output or furthjer considerations
	 * @param result a Vector containing 3 values: a) right classification ba used learner, 
	 * b) lerner could not decide or c) learner found wrong concept
	 */
	private void evalResult(Vector<Integer> result) {
		// TODO hier muss mehr Auswertung passieren, insbes: Vertrauensintervalle etc
		System.out.println("Learning result: \n correct: "+result.get(0)+"\n unknown: "+result.get(1)+"\n wrong: "+result.get(2));
	}
	/** evaluate the learner with a given test set. 
	 * 
	 * @param list: The set of test examples containing the correct concept
	 * @param learner: The learner to be tests
	 * 
	 * @return a vector containing the test results: success, unknown, false
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
		// TODO: die Reihenfolge der Elemente zuf�llig ver�ndern
		return vectors;
	}

	/**
	 * Split the set of festure vectors in a set of traing data and a set of test data.
	 * For representative results it is essential to mix the order of vectors 
	 * before splitting the set
	 * 
	 * @param vectors :a List fo Feature Vectors we can use for the test
	 * @return a List containt two Lists: first the training data, second the test data they are disjunct subsets of vector 
	 *
	 */
	private List<List<FeatureVector>> extractTrainingData(
			List<FeatureVector> vectors) {
		List<List<FeatureVector>> result = new ArrayList<>();
		List<FeatureVector> trainingData = new ArrayList<>();
		List<FeatureVector> testData = new ArrayList<>();
		
		int cut = (int) ((testRate / 100f) * vectors.size());
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
	 * @param args 1. filename of Serialiszed List<FeatureVector>
	 */
	public static void main(String[] args){
		/*String filename=null;
		if(args.length==0){
			System.out.println("No data file provided, using dummy data: DummyData.dat");
			filename = "DummyData.dat";
		}
		else 
			filename = args[0];
		new Evaluator(filename);*/


        URL url = EvaluatorDaniel.class.getResource("/zDummyData.dat");

        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        new EvaluatorDaniel(file.getAbsolutePath());
    }
}

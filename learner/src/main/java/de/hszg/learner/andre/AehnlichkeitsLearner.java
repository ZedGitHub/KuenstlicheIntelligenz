package de.hszg.learner.andre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.hszg.learner.featureVector.FeatureVector;

/**
 * 
 * @author Andre Krause
 *
 */

public class AehnlichkeitsLearner implements Learner{

	private List<LearnedFeatureVector> learnedVectorList = new ArrayList<LearnedFeatureVector>();
	
	@Override
	public void learn(List<FeatureVector> trainingSet) {
		
		for(int i = 1; i<Concept.values().length; i++){
			learnedVectorList.add(calculateLearnedVector(splitFeatureVector(trainingSet, Concept.values()[i]), Concept.values()[i]));
		}
	
	}

	@Override
	public Concept classify(FeatureVector example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private LearnedFeatureVector calculateLearnedVector(List<FeatureVector> splitFeatureVector, Concept concept) {

		double featureSum = 0.0;
		List<Double> featureList = new ArrayList<Double>();
		
		for(int i = 0; i < splitFeatureVector.get(0).getNumFeatures(); i++){

			featureSum = 0;
			
			for(int j =0; j<splitFeatureVector.size(); j++){
				featureSum+=splitFeatureVector.get(j).getFeatureValue(i);
			}
			
			if(featureSum == 0){
				featureList.add(0.0);
			}
			else{
				featureList.add(featureSum/splitFeatureVector.size());
			}
		}
		
		LearnedFeatureVector tempVector = new LearnedFeatureVector(featureList, concept);
		
		return tempVector;
	}

	private List<FeatureVector> splitFeatureVector(List<FeatureVector >featureVectorList, Concept concept){
		
		List<FeatureVector> temp = featureVectorList.stream().filter(f -> f.getConcept().equals(concept)).collect(Collectors.toList());
		
		return temp;
	}
	
	public class LearnedFeatureVector{

		private Concept concept;
		private double[] feature;
		
		public LearnedFeatureVector(List<Double> featureList, Concept conceptInput){
			feature = new double[featureList.size()];
			
			for(int i = 0; i<featureList.size(); i++){
				feature[i] = featureList.get(i);
			}
			
			concept = conceptInput;
		}
		
		public Concept getConcept() {
			return concept;
		}

		public double getNumFeatures() {
			return feature.length;
		}

		public double getFeatureValue(int i) {
			return feature[i];
		}
		
	}
}



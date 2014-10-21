package de.hszg.learner;

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

	private List<FeatureVector> learnedVectorList = new ArrayList<FeatureVector>();
	
	@Override
	public void learn(List<FeatureVector> trainingSet) {
		
		for(int i = 0; i<Concept.values().length; i++){
			learnedVectorList.add(calculateLearnedVector(splitFeatureVector(trainingSet, Concept.values()[i]), Concept.values()[i]));
		}
	
	}

	@Override
	public Concept classify(FeatureVector example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private FeatureVector calculateLearnedVector(List<FeatureVector> splitFeatureVector, Concept concept) {

		int featureSum = 0;
		List<Integer> featureList = new ArrayList<Integer>();
		
		for(int i = 0; i < splitFeatureVector.get(0).getNumFeatures(); i++){

			featureSum = 0;
			
			for(int j =0; j<splitFeatureVector.size(); j++){
				featureSum+=splitFeatureVector.get(j).getFeatureValue(i);
			}
			
			if(featureSum == 0){
				featureList.add(0);
			}
			else{
				featureList.add(featureSum/splitFeatureVector.size());
			}
		}
		
		FeatureVector tempVector = new LearnedFeatureVector(featureList, concept);
		
		return tempVector;
	}

	private List<FeatureVector> splitFeatureVector(List<FeatureVector >featureVectorList, Concept concept){
		
		List<FeatureVector> temp = featureVectorList.stream().filter(f -> f.getConcept().equals(concept)).collect(Collectors.toList());
		
		return temp;
	}
	
	public class LearnedFeatureVector implements FeatureVector {

		private Concept concept;
		private int[] feature;
		
		public LearnedFeatureVector(List<Integer> featureList, Concept conceptInput){
			feature = new int[featureList.size()];
			
			for(int i = 0; i<featureList.size(); i++){
				feature[i] = featureList.get(i);
			}
			
			concept = conceptInput;
		}
		
		@Override
		public Concept getConcept() {
			return concept;
		}

		@Override
		public int getNumFeatures() {
			return feature.length;
		}

		@Override
		public int getFeatureValue(int i) {
			return feature[i];
		}
		
	}
}



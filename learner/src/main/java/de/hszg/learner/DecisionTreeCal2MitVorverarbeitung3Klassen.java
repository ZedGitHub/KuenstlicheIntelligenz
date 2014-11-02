package de.hszg.learner;

import java.util.LinkedList;
import java.util.List;

import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.featureVector.robertRiedel.FeatureVectorR;
import de.hszg.learner.featureVector.zed.FeatureVectorZ;
import de.hszg.learner.tobias.Cal2DecisionTreeNode;

public class DecisionTreeCal2MitVorverarbeitung3Klassen implements Learner {

	private Cal2DecisionTreeNode tree = null;
	
	public DecisionTreeCal2MitVorverarbeitung3Klassen(){
		tree = new Cal2DecisionTreeNode();
	}
	
	@Override
	public void learn(List<FeatureVector> trainingSet) {
		List<FeatureVector> newTrainingSet = new LinkedList<>();
		int length = trainingSet.size();
		for(int i = 0; i < length; i++){
			newTrainingSet.add(cluster(trainingSet.get(i)));
		}
		int lenghtNew = newTrainingSet.size();
		int changes = 0;
		do{
			changes = 0;
			for(int i = 0; i < lenghtNew; i++){
				if(tree.learn(newTrainingSet.get(i))){
					changes++;
				}
//				System.out.println(tree.toString());
			}
//			System.out.println("");
		}while(changes > 0);				
	}

	@Override
	public Concept classify(FeatureVector example) {
		FeatureVector trainingVector = cluster(example);
		return tree.findConcept(trainingVector);
	}
	
	/**
	 * 
	 * @param vector
	 * @return
	 */
	private FeatureVector cluster(FeatureVector vector){
		FeatureVector vec = null;
		if(vector.getClass().equals(FeatureVectorZ.class)){
			int[] features = new int[vector.getNumFeatures()];
			for(int i = 0; i < vector.getNumFeatures(); i++){
				if(vector.getFeatureValue(i) <= 33){
					features[i]=33;
				}else if(vector.getFeatureValue(i) > 33 & vector.getFeatureValue(i) <= 66){
					features[i]=66;
				}else if(vector.getFeatureValue(i) > 66 & vector.getFeatureValue(i) <= 100){
					features[i]=100;
				}
			}
			vec = new FeatureVectorZ(features, vector.getConcept());
		}else if(vector.getClass().equals(FeatureVectorR.class)){
			int[] features = new int[vector.getNumFeatures()];
			for(int i = 0; i < vector.getNumFeatures(); i++){
				if(i == 0 | i == 5 | i == 6){
					features[i] = vector.getFeatureValue(i);
				}else{
					if(vector.getFeatureValue(i) <= 33){
						features[i]=33;
					}else if(vector.getFeatureValue(i) > 33 & vector.getFeatureValue(i) <= 66){
						features[i]=66;
					}else if(vector.getFeatureValue(i) > 66 & vector.getFeatureValue(i) <= 100){
						features[i]=100;
					}
				}
			}
			vec = new FeatureVectorR(features, vector.getConcept());
		}else{
			vec = vector;
		}
		return vec;
	}
}

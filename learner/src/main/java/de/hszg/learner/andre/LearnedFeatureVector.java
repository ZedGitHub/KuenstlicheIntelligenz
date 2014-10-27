package de.hszg.learner.andre;

import java.util.List;

import de.hszg.learner.Concept;

public class LearnedFeatureVector {
	
	public Concept concept;
	public double[] feature;

	public LearnedFeatureVector(List<Double> featureList, Concept conceptInput) {
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
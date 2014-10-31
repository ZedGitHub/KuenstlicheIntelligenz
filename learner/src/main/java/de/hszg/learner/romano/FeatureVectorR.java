package de.hszg.learner.romano;

import de.hszg.learner.Concept;

public class FeatureVectorR{

	private Concept concept;
	private int numberOfFeatures;
	private int[] feature;
	
	public FeatureVectorR(int numberOfFeatures){
		
		this.numberOfFeatures = numberOfFeatures;
		this.feature = new int[numberOfFeatures];
	}

	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public int[] getFeature() {
		return feature;
	}

	public void setFeature(int idx, int value) {
		this.feature[idx] = value;
	}
	
	public int getNumFeatures() {
		return this.numberOfFeatures;
	}

	public int getFeatureValue(int i) {
		return this.feature[i];
	}
	

}

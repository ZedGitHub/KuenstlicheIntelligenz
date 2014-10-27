package de.hszg.learner.andre;

import java.util.ArrayList;
import java.util.List;

import de.hszg.learner.Concept;
import de.hszg.learner.Learner;
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
			learnedVectorList.add(LearnFromVector.splitAndcalculateLearnedVector(trainingSet, Concept.values()[i]));
		}
	
	}

	@Override
	public Concept classify(FeatureVector example) {
		return ClassifyFromVector.classifyConcept(example, learnedVectorList);
	}
	
	
}



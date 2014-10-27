package de.hszg.learner.andre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;

/**
 * 
 * @author Andre Krause
 *
 */

public class LearnFromVector {

	public static LearnedFeatureVector splitAndcalculateLearnedVector(List<FeatureVector> featureVectorList, Concept concept) {

		List<FeatureVector> temp = featureVectorList.stream().filter(f -> f.getConcept().equals(concept)).collect(Collectors.toList());
		
		double featureSum = 0.0;
		List<Double> featureList = new ArrayList<Double>();
		
		for(int i = 0; i < temp.get(0).getNumFeatures(); i++){

			featureSum = 0;
			
			for(int j =0; j<temp.size(); j++){
				featureSum+=temp.get(j).getFeatureValue(i);
			}
			
			if(featureSum == 0){
				featureList.add(0.0);
			}
			else{
				featureList.add(featureSum/temp.size());
			}
		}
		
		LearnedFeatureVector tempVector = new LearnedFeatureVector(featureList, concept);
		
		return tempVector;
	}
}

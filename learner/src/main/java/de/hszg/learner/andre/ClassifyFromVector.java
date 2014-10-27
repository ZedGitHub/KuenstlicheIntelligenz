package de.hszg.learner.andre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;

/**
 * 
 * @author Andre Krause
 *
 */

public class ClassifyFromVector {

	private static Map<Concept, Double> distanceToConceptMap = new HashMap<Concept, Double>();
	private static double[] distanceArray = new double[Concept.values().length-1];
	
	private static FeatureVector example = null;
	private static List<LearnedFeatureVector> learnedVectorList = null;
	
	public static Concept classifyConcept(FeatureVector exampleInput, List<LearnedFeatureVector> learnedVectorListInput){
		example = exampleInput;
		learnedVectorList = learnedVectorListInput;
		
		calculateDistancesToLearnedVector();
		
		Arrays.sort(distanceArray);
		
		if(checkLeastDistanceUnique(distanceArray[0])>1){
			return Concept.Unknown;
		}
		else{
			return getLeastDistanceConcept(distanceArray[0]);
		}
	}
	
	private static void calculateDistancesToLearnedVector(){
		
		/**
		 * d = sqrt((x2-x1)² + (y2-y1)² + (z2-z1)²  + ... )
		 */
		
		double distancePerVector = 0.0;
		
		for(int i = 0; i < learnedVectorList.size(); i++){
			distancePerVector = 0.0;
			
			LearnedFeatureVector temp = learnedVectorList.get(i);
			
			for(int j = 0; j < temp.getNumFeatures(); j++){
				distancePerVector += (example.getFeatureValue(j) - temp.getFeatureValue(j)) * (example.getFeatureValue(j) - temp.getFeatureValue(j));
			}
			
			distanceToConceptMap.put(temp.getConcept(), Math.sqrt(distancePerVector));
			distanceArray[i] = Math.sqrt(distancePerVector);
		}
		
	}
	
	private static int checkLeastDistanceUnique(double leastDistance){
		int count = 0;
		
		Iterator<Double> iterator = distanceToConceptMap.values().iterator();
		while(iterator.hasNext()){
			if(iterator.next() == leastDistance)
				count++;
		}
		
		return count;
	}
	
	private static Concept getLeastDistanceConcept(double leastDistance){
		Concept temp = Concept.Unknown;
		
		for(int i = 1; i < Concept.values().length; i++){
			if(distanceToConceptMap.get(Concept.values()[i]) == leastDistance){
				temp = Concept.values()[i];
			}
		}
		
		return temp;
	}
}

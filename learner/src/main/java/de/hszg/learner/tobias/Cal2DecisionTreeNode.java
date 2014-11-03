package de.hszg.learner.tobias;

import java.util.LinkedList;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;

public class Cal2DecisionTreeNode {

	private Concept concept = Concept.Unknown;	//Concept des Knotens, Unkown ist Default (Sonst-Zweig)
	private int feature = -1;	//betrachtetes Merkmal, -1 Knoten ist Endknoten = Concept
	private LinkedList<Integer> attributes = new LinkedList<Integer>();	//Attribute der Nachfolger
	private LinkedList<Cal2DecisionTreeNode> nextNodes = new LinkedList<Cal2DecisionTreeNode>();	//Referenzen der Nachfolger
	
	public Cal2DecisionTreeNode(){
		
	}
	public Cal2DecisionTreeNode(Concept concept){
		this.concept = concept;
	}
	
	/**
	 * find the concept for the {@link FeatureVector} in the decision tree
	 * @param featureVector {@link FeatureVector}, for which the concept is searched
	 * @return the concept, which was found for the {@link FeatureVector}
	 */
	public Concept findConcept(FeatureVector featureVector){
		int decision = -1;
		decision = findDecision(featureVector);
		if(decision < 0){
			return concept;
		}else{
			return nextNodes.get(decision).findConcept(featureVector);
		}
	}
	
	/**
	 * Method to train the decision tree
	 * @param featureVector {@link FeatureVector} which should be learned
	 * @return <code>true</code> if the decision tree was changed, otherwise <code>false</code>
	 */
	public boolean learn(FeatureVector featureVector){
		return learn(featureVector,-1);
	}
	
	/**
	 * Method to train the decision tree (recursive)
	 * @param featureVector {@link FeatureVector} which should be learned
	 * @param lastFeature Feature, from the last level of the decision tree
	 * @return <code>true</code> if the decision tree was changed, otherwise <code>false</code>
	 */
	private boolean learn(FeatureVector featureVector, int lastFeature){
		//Klassifiziere Objekt
		int decision = -1;
		decision = findDecision(featureVector);
		if(decision < 0){
			//wenn Unbekannt
			if(concept == Concept.Unknown & feature == -1){
				//Konzept eintragen
				concept = featureVector.getConcept();
				//nächstes Objekt
				return true;
			}
			//wenn richtig erkannt
			else if (concept == featureVector.getConcept()) {
				//nächstes Objekt
				return false;
			}
			//wenn falsch erkannt
			else if (concept != featureVector.getConcept()){
				// wenn noch Merkmale frei
				if(lastFeature < featureVector.getNumFeatures() - 1){
					//ersetze Konzept durch Merkmal und trage Merkmalsausprägung ein
					feature = lastFeature +1;
					concept = Concept.Unknown;
					attributes.add(featureVector.getFeatureValue(feature));
					nextNodes.add(new Cal2DecisionTreeNode(featureVector.getConcept()));
					//nächstes Objekt
					return true;
				}
				// wenn kein Merkmal mehr frei
					//nächstes Objekt
			}	
		}else{
			return nextNodes.get(decision).learn(featureVector,feature);		
		}
		return false;
	}
	
	/**
	 * 
	 * @param featureVector
	 * @return an int value, which represent the next subtree; >0 for the next subtree, -1 if there is not a next subtree
	 */
	private int findDecision(FeatureVector featureVector) {
		int length = attributes.size();
		int decision = -1;
		for(int i = 0; i < length; i++){
			if(featureVector.getFeatureValue(feature) == attributes.get(i)){
				decision = i;
				break;
			}
		}
		return decision;
	}
	
	@Override
	public String toString() {
		return "Cal2DecisionTree [concept=" + concept + ", merkmal=" + feature
				+ ", attributes=" + attributes + ", nextNodes=" + nextNodes + "]";
	}
}

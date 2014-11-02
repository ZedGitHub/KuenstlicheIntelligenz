package de.hszg.learner;

import java.util.List;

import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.tobias.Cal2DecisionTreeNode;

public class DecisionTreeCal2 implements Learner {

	private Cal2DecisionTreeNode tree = null;
	
	public DecisionTreeCal2(){
		tree = new Cal2DecisionTreeNode();
	}
	
	@Override
	public void learn(List<FeatureVector> trainingSet) {
		int length = trainingSet.size();
		int changes = 0;
		do{
			changes = 0;
			for(int i = 0; i < length; i++){
				if(tree.learn(trainingSet.get(i))){
					changes++;
				}
//				System.out.println(tree.toString());
			}
//			System.out.println("");
		}while(changes > 0);				
	}

	@Override
	public Concept classify(FeatureVector example) {
		return tree.findConcept(example);
	}
}

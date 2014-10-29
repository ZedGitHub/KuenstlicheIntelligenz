package de.hszg.learner.romano;


import java.util.LinkedList;
import java.util.List;

import de.hszg.learner.Concept;
import de.hszg.learner.Learner;
import de.hszg.learner.featureVector.DummyFeatureVector;
import de.hszg.learner.featureVector.FeatureVector;

public class Id3 implements Learner {
	
	private Node root;
	private int numberOfVector = 0;
	private int numberOfAttribute = 0;
	private int featureValue = 0;
	
	public double calcEntropy(List<FeatureVector> trainingSet){

		double linksAbbigen = 0;
		double rechtsAbbiegen = 0;
		double stop = 0;
		double vorfahrtGewaehren = 0;
		double vorfahrtstrasse = 0;
		double vorfahrtVonRechts = 0;
		double unbekannt = 0;
		
		for(int x = 0; x < trainingSet.size(); x++){
			
			if(trainingSet.get(x).getConcept() == Concept.LinksAbbiegen){
				linksAbbigen++;
			}
			else if(trainingSet.get(x).getConcept() == Concept.RechtsAbbiegen){
				rechtsAbbiegen++;
			}
			else if(trainingSet.get(x).getConcept() == Concept.Stop){
				stop++;	
			}
			else if(trainingSet.get(x).getConcept() == Concept.VorfahrtGewaehren){
				vorfahrtGewaehren++;
			}
			else if(trainingSet.get(x).getConcept() == Concept.Vorfahrtsstrasse){
				vorfahrtstrasse++;
			}
			else if(trainingSet.get(x).getConcept() == Concept.VorfahrtVonRechts){
				vorfahrtVonRechts++;
			}
			else{
				unbekannt++;
			}
		}
		
		linksAbbigen = linksAbbigen/trainingSet.size();
		rechtsAbbiegen = rechtsAbbiegen/trainingSet.size();
		stop = stop/trainingSet.size();
		vorfahrtGewaehren = vorfahrtGewaehren/trainingSet.size();
		vorfahrtstrasse = vorfahrtstrasse/trainingSet.size();
		vorfahrtVonRechts = vorfahrtVonRechts/trainingSet.size();
		unbekannt = unbekannt/trainingSet.size();
		
		if(linksAbbigen > 0){
			linksAbbigen = (linksAbbigen * Math.log(linksAbbigen))/Math.log(2);
		}
		if(rechtsAbbiegen > 0){
			rechtsAbbiegen = (rechtsAbbiegen * Math.log(rechtsAbbiegen))/Math.log(2);
		}
		if(stop > 0){
			stop = (stop * Math.log(stop))/Math.log(2);
		}
		if(vorfahrtGewaehren > 0){
			vorfahrtGewaehren = (vorfahrtGewaehren * Math.log(vorfahrtGewaehren))/Math.log(2);
		}
		if(vorfahrtstrasse > 0){
			vorfahrtstrasse = (vorfahrtstrasse * Math.log(vorfahrtstrasse))/Math.log(2);
		}
		if(vorfahrtVonRechts > 0){
			vorfahrtVonRechts = (vorfahrtVonRechts * Math.log(vorfahrtVonRechts))/Math.log(2);
		}
		if(unbekannt > 0){
			unbekannt = (unbekannt * Math.log(unbekannt))/Math.log(2);
		}
		
		double result = (-1*linksAbbigen) - rechtsAbbiegen - stop - vorfahrtGewaehren - vorfahrtstrasse - vorfahrtVonRechts - unbekannt;
		
		return result;
	}
	
	public double calcInfoGain(){
		
		return 0;
	}
	
	public List<FeatureVectorR> normalizeVectorList(List<FeatureVector> trainingSet){
		
		int[][] arr = new int[trainingSet.size()][trainingSet.get(0).getNumFeatures()];
		List<FeatureVectorR> fvrList = new LinkedList<FeatureVectorR>();
		double range = 0;
		
		for(int i = 0; i < trainingSet.size(); i++){
			
			for (int j = 0; j < trainingSet.get(i).getNumFeatures(); j++){
				
				arr[i][j] = trainingSet.get(i).getFeatureValue(j);
			}
		}
		
		int maxValue = 0;
		
		for(int i = 0; i < trainingSet.get(0).getNumFeatures(); i++){
			
			maxValue = 0;
			
			for (int j = 0; j < trainingSet.size(); j++){
				
				if(maxValue < arr[j][i]){
					
					maxValue = arr[j][i];
				}
			}
			
			for (int j = 0; j < trainingSet.size(); j++){
				
				range = maxValue/4;
				
				if(arr[j][i] > 4){
					
					if(arr[j][i] >= 0 && arr[j][i] < range){
						arr[j][i] = 1;
					}
					else if(arr[j][i] >= range && arr[j][i] < (range*2)){
						arr[j][i] = 2;
					}
					else if(arr[j][i] >= (range*2) && arr[j][i] < (range*3)){
						arr[j][i] = 3;
					}
					else{
						arr[j][i] = 4;
					}	
				}
			}
		}
		
		for(int i = 0; i < trainingSet.size(); i++){
			
			FeatureVectorR fvr = new FeatureVectorR(trainingSet.get(i).getNumFeatures());
			fvr.setConcept(trainingSet.get(i).getConcept());
			
			for (int j = 0; j < trainingSet.get(0).getNumFeatures(); j++){

				fvr.setFeature(j, arr[i][j]);
			}
			
			fvrList.add(fvr);
			
			for(int x = 0; x < fvr.getNumFeatures(); x++){
				System.out.println(fvr.getFeatureValue(x));
			}
		}
		
		return fvrList;
	}
	
	public Node createTree(Node node, List<FeatureVectorR> trainingSet){
		
			if(numberOfVector + 1 > trainingSet.size()){
				
				numberOfVector = 0;
				numberOfAttribute = 0;
				return null;
			}
			else{
				int j = node.getChildren().size();
				if(j != 0 ){
					j--;
				}
				for(int i = 0; i <= j; i++){
					
					//exisitiert bereits die Kante
					if(!node.getChildren().isEmpty()){
						if(node.getChildren().get(i).getEdge() == trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute)){
							
							//letztes Attribut um Vektor erreicht --> Blatt // gehen zum nächsten Vektor
							if(numberOfAttribute == trainingSet.get(numberOfVector).getNumFeatures()){
								node.getChildren().get(i).setConcept(trainingSet.get(numberOfVector).getConcept());
								System.out.println("SET LEAF " + trainingSet.get(numberOfVector).getConcept() );
								numberOfVector++;
								numberOfAttribute = 0;
								
								return createTree(root, trainingSet);
							}
							else{
								
								numberOfAttribute++;
								return createTree(node.getChildren().get(i), trainingSet);
							}
						}
						if(numberOfAttribute == trainingSet.get(numberOfVector).getNumFeatures()){
							node.setConcept(trainingSet.get(numberOfVector).getConcept());
							System.out.println("SET LEAF " + trainingSet.get(numberOfVector).getConcept() );
							numberOfVector++;
							numberOfAttribute = 0;
							
							return createTree(root, trainingSet);
						}
						else{
							Node temp = new Node(node, trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute), null);
							node.setChildren(temp);
							System.out.println("SET NEW CHILD " +  trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute));
							numberOfAttribute++;
							return createTree(temp, trainingSet);
						}
					}
					else{
						if(numberOfAttribute == trainingSet.get(numberOfVector).getNumFeatures()){
							node.setConcept(trainingSet.get(numberOfVector).getConcept());
							System.out.println("SET LEAF " + trainingSet.get(numberOfVector).getConcept() );
							numberOfVector++;
							numberOfAttribute = 0;
							
							return createTree(root, trainingSet);
						}
						else{
							Node temp = new Node(node, trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute), null);
							node.setChildren(temp);
							System.out.println("SET NEW CHILD " +  trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute));
							numberOfAttribute++;
							return createTree(temp, trainingSet);
						}
					}
				}
			}

		return null;
	}
	
	public Concept classifyVector(Node node, FeatureVector example){
		
		for(int i = 0; i < node.getChildren().size(); i++){
			
			if(node.getChildren().get(i).getEdge() == example.getFeatureValue(featureValue)){
				
				if(node.getChildren().get(i).getConcept() != null){
					
					System.out.println(node.getChildren().get(i).getConcept());
					return node.getChildren().get(i).getConcept();
				}
				else{
					featureValue++;
					return classifyVector(node.getChildren().get(i), example);
				}
			}
		}
		System.out.println(Concept.Unknown);
		return Concept.Unknown;
	}
	
	public static void main(String args[]){
		
		FeatureVector[] f = new FeatureVector[2];
		f[0] = new DummyFeatureVector(1,40,40,Concept.VorfahrtGewaehren);
		f[1] = new DummyFeatureVector(1,80,40,Concept.Unknown);
		/*
		f[2] = new DummyFeatureVector(60,5,6,Concept.Vorfahrtsstrasse);
		f[3] = new DummyFeatureVector(1,5,3,Concept.RechtsAbbiegen);
		f[4] = new DummyFeatureVector(66,2,3,Concept.Stop);
		f[5] = new DummyFeatureVector(66,3,1,Concept.LinksAbbiegen);
		f[6] = new DummyFeatureVector(66,4,1,Concept.VorfahrtVonRechts);
		*/
		List<FeatureVector> res = new LinkedList<>();
		for(FeatureVector fv : f) res.add(fv);
		
		Id3 id3 = new Id3();
		id3.learn(res);
		
		DummyFeatureVector test = new DummyFeatureVector(66,5,6,Concept.Stop);
		id3.classify(test);
	}

	@Override
	public void learn(List<FeatureVector> trainingSet) {
		
		root = new Node(null, 999, null);
		System.out.println("SET ROOT");
		//System.out.println(calcEntropy(trainingSet));
		createTree(root, normalizeVectorList(trainingSet));
	}

	@Override
	public Concept classify(FeatureVector example) {

		return classifyVector(root, example);
	}


}

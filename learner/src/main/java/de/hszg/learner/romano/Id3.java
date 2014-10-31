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
	private double backup[];
	private int featureValue = 0;
	private double save[];
	private double globalrange[];
	private double gloabelMaxValue[];
	private boolean classify = false;
	
	public Id3(){
	
	}
	
	public List<FeatureVectorR> calcEntropy(List<FeatureVectorR> trainingSet){

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
		
		
		double gainArr[] = new double[trainingSet.get(0).getNumFeatures()];
		
		for(int i = 0; i < trainingSet.get(0).getNumFeatures(); i++){
			
		double one = 0;
		double two = 0;
		double three = 0;
		double four = 0;
		double temp = 0;
		double all = 0;
		double gain = 0;
			
			for(int j = 0; j < trainingSet.size(); j++){
				
				if(trainingSet.get(j).getFeatureValue(i) == 1){
					
					one++;
				}
				else if(trainingSet.get(j).getFeatureValue(i) == 2){
					
					two++;
				}
				else if(trainingSet.get(j).getFeatureValue(i) == 3){
					
					three++;
				}
				else if(trainingSet.get(j).getFeatureValue(i) == 4){
					
					four++;
				}
			}
			
			all = one + two + three + four;
			
			double tempOne = 0;
			double tempTwo = 0;
			double tempThree = 0;
			double tempFour = 0;
			
			double onetemp = 0;
			double twotemp = 0;
			double threetemp = 0;
			double fourtemp = 0;
			
			if(all > 0){
				onetemp = one/all;
				twotemp = two/all;
				threetemp = three/all;
				fourtemp = four/all;
			}
			
			if(onetemp > 0){
				tempOne = (one/all) * (Math.log(one/all)/Math.log(2));
			}
			if(twotemp > 0){
				tempTwo = (two/all) * (Math.log(two/all)/Math.log(2));
			}
			if(threetemp > 0){
				tempThree = (three/all) * (Math.log(three/all)/Math.log(2));
			}
			if(fourtemp > 0){
				tempFour = (four/all) * (Math.log(four/all)/Math.log(2));
			}
			
			temp = tempOne + tempTwo + tempThree + tempFour;
			gain = result - temp;
			gainArr[i] = gain;
		}
		
		save = new double[gainArr.length];
		
		for(int z = 0; z < gainArr.length; z++){
			
			save[z] = gainArr[z];
		}
		
		boolean swapped = true;
	    int j = 0;
	    double tmp;
	    int real;
	    while (swapped) {
	        swapped = false;
	        j++;
	        for (int i = 0; i < gainArr.length - j; i++) {
	            if (gainArr[i] > gainArr[i + 1]) {
	            	
	            	for(int x = 0; x < trainingSet.size(); x++){
	            		tmp = gainArr[i];
	            		real = trainingSet.get(x).getFeatureValue(i);
	            		
	            		gainArr[i] = gainArr[i + 1];
	            		trainingSet.get(x).setFeature(i, trainingSet.get(x).getFeatureValue(i + 1));
	            		
	            		gainArr[i + 1] = tmp;
	            		trainingSet.get(x).setFeature(i + 1, real);
	            		
	                swapped = true;
	            	}
	            }
	        }
	    }
	    
		return trainingSet;
	}
	
	public List<FeatureVectorR> normalizeVectorList(List<FeatureVector> trainingSet){
		
		int[][] arr = new int[trainingSet.size()][trainingSet.get(0).getNumFeatures()];
		List<FeatureVectorR> fvrList = new LinkedList<FeatureVectorR>();
		
		for(int i = 0; i < trainingSet.size(); i++){
			
			for (int j = 0; j < trainingSet.get(i).getNumFeatures(); j++){
				
				arr[i][j] = trainingSet.get(i).getFeatureValue(j);
			}
		}
		
		for(int i = 0; i < trainingSet.get(0).getNumFeatures(); i++){
			
			if( gloabelMaxValue == null){
				gloabelMaxValue = new double[trainingSet.get(0).getNumFeatures()];
			
			}
			if(classify == false){
				for (int j = 0; j < trainingSet.size(); j++){
					
					if(gloabelMaxValue[i] < arr[j][i]){
						
						gloabelMaxValue[i] = arr[j][i];
					}
				}
			}
			
			for (int j = 0; j < trainingSet.size(); j++){
				
				if(globalrange == null){
					globalrange = new double[trainingSet.get(0).getNumFeatures()];
				}
					
				if(classify == false){
					globalrange[i] = gloabelMaxValue[i]/7.0;
				}
				
				if(gloabelMaxValue[i] > 7){
					
					if(arr[j][i] >= 0 && arr[j][i] < globalrange[i]){
						arr[j][i] = 1;
					}
					else if(arr[j][i] >= globalrange[i] && arr[j][i] < (globalrange[i]*2)){
						arr[j][i] = 2;
					}
					else if(arr[j][i] >= (globalrange[i]*2) && arr[j][i] < (globalrange[i]*3)){
						arr[j][i] = 3;
					}
					else if(arr[j][i] >= (globalrange[i]*3) && arr[j][i] < (globalrange[i]*4)){
						arr[j][i] = 4;
					}
					else if(arr[j][i] >= (globalrange[i]*4) && arr[j][i] < (globalrange[i]*5)){
						arr[j][i] = 5;
					}
					else if(arr[j][i] >= (globalrange[i]*5) && arr[j][i] < (globalrange[i]*6)){
						arr[j][i] = 6;
					}
					else{
						arr[j][i] = 7;
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
					
					if(!node.getChildren().isEmpty()){
						if(node.getChildren().get(i).getEdge() == trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute)){
							
							if(numberOfAttribute == trainingSet.get(numberOfVector).getNumFeatures()){
								node.getChildren().get(i).setConcept(trainingSet.get(numberOfVector).getConcept());
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
							numberOfVector++;
							numberOfAttribute = 0;
							
							return createTree(root, trainingSet);
						}
						else{
							Node temp = new Node(node, trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute), null);
							node.setChildren(temp);
							numberOfAttribute++;
							return createTree(temp, trainingSet);
						}
					}
					else{
						if(numberOfAttribute == trainingSet.get(numberOfVector).getNumFeatures()){
							node.setConcept(trainingSet.get(numberOfVector).getConcept());
							numberOfVector++;
							numberOfAttribute = 0;
							
							return createTree(root, trainingSet);
						}
						else{
							Node temp = new Node(node, trainingSet.get(numberOfVector).getFeatureValue(numberOfAttribute), null);
							node.setChildren(temp);
							numberOfAttribute++;
							return createTree(temp, trainingSet);
						}
					}
				}
			}

		return null;
	}
	
	public Concept classifyVector(Node node, FeatureVectorR example){
		
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
	
	/*
	 * Main for Testing
	 
	
	public static void main(String args[]){
		
		FeatureVector[] f = new FeatureVector[3];
	
		f[2] = new DummyFeatureVector(33,22,11,Concept.VorfahrtGewaehren);
		f[0] = new DummyFeatureVector(20,5,2,Concept.LinksAbbiegen);
		f[1] = new DummyFeatureVector(80,20,20,Concept.Stop);
		
		List<FeatureVector> res = new LinkedList<>();
		for(FeatureVector fv : f) res.add(fv);
		
		Id3 id3 = new Id3();
		id3.learn(res);
		
		DummyFeatureVector test = new DummyFeatureVector(20,5,2,Concept.Stop);
		id3.classify(test);
		DummyFeatureVector test2 = new DummyFeatureVector(20,5,2,Concept.Stop);
		id3.classify(test2);
		DummyFeatureVector test3 = new DummyFeatureVector(68,20,20,Concept.Stop);
		id3.classify(test3);
	}
	
	*/


	@Override
	public void learn(List<FeatureVector> trainingSet) {
		
		root = new Node(null, 999, null);
		System.out.println("ID3 learn...");
		this.classify = false;
		createTree(root, calcEntropy(normalizeVectorList(trainingSet)));
	}

	@Override
	public Concept classify(FeatureVector example) {
		
		System.out.println("ID3 classify...");
		this.classify = true;
		
		List<FeatureVector> list = new LinkedList<FeatureVector>();
		list.add(example);
		FeatureVectorR fvr = normalizeVectorList(list).get(0);
		
		if(save != null){
		
			backup = new double[save.length];
			
			for(int z = 0; z < save.length; z++){
				backup[z] = save[z];
			}
			
			boolean swapped = true;
		    int j = 0;
		    double tmp;
		    int real;
		    while (swapped) {
		        swapped = false;
		        j++;
		        for (int i = 0; i < save.length - j; i++) {
		            if (save[i] > save[i + 1]) {
		            	
		            		tmp = save[i];
		            		real = fvr.getFeatureValue(i);
		            		
		            		save[i] = save[i + 1];
		            		fvr.setFeature(i, fvr.getFeatureValue(i + 1));
		            		
		            		save[i + 1] = tmp;
		            		fvr.setFeature(i + 1, real);
		            		
		                swapped = true;
		            }
		        }
		    }
		    for(int z = 0; z < backup.length; z++){
				
				save[z] = backup[z];
			}
		}
	    featureValue = 0;
		
		return classifyVector(root, fvr);
	}
}

package de.hszg.learner.dataCreator.zed;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.featureVector.zed.FeatureVectorZ;

/**
 * Creates a {@value #filename} file in {@value #pathToDataFile} containing
 * Feature Vectors of {@value #numberOfVectorsToCreate} randomly chosen files
 * from {@value #pathToSigns} To create a File {@value #dummyFilename} pass the
 * argument "dummy".
 * 
 * @author z
 *
 */
public class DataCreatorZ {

	private static final String filename = "zdata.dat";
	private static final String dummyFilename = "zDummyData.dat";

	private static final String pathToSigns = "/home/z/Studium/KI/Verkehrszeichen/";
	private static final String pathToDataFile = "src/main/resources/";

	private static final int numberOfVectorsToCreate = 1000;
	
	private ImageProcessorZ processor = new ImageProcessorZ();

	public DataCreatorZ(boolean dummy) {
		if (dummy) {
			writeDataFile(createDummyData(), dummyFilename);
		} else {
			writeDataFile(createRandomData(), filename);
		}
	}

	private List<FeatureVector> createDummyData() {
		List<FeatureVector> result = new LinkedList<>();
		result.add(new FeatureVectorZ(new int[] { 1, 1, 2, 1, 75, 80 },
				Concept.Stop));
		result.add(new FeatureVectorZ(new int[] { 4, 2, 3, 1, 90, 85 },
				Concept.Stop));
		result.add(new FeatureVectorZ(new int[] { 0, 0, 0, 0, 70, 70 },
				Concept.Stop));

		result.add(new FeatureVectorZ(new int[] { 0, 50, 75, 0, 0, 0 },
				Concept.LinksAbbiegen));
		result.add(new FeatureVectorZ(new int[] { 1, 20, 40, 1, 0, 1 },
				Concept.LinksAbbiegen));
		result.add(new FeatureVectorZ(new int[] { 0, 30, 80, 0, 2, 0 },
				Concept.LinksAbbiegen));

		result.add(new FeatureVectorZ(new int[] { 0, 50, 20, 0, 0, 0 },
				Concept.RechtsAbbiegen));
		result.add(new FeatureVectorZ(new int[] { 0, 90, 70, 0, 1, 0 },
				Concept.RechtsAbbiegen));
		result.add(new FeatureVectorZ(new int[] { 0, 76, 70, 3, 0, 2 },
				Concept.RechtsAbbiegen));

		result.add(new FeatureVectorZ(new int[] { 0, 0, 0, 20, 30, 50 },
				Concept.VorfahrtVonRechts));
		result.add(new FeatureVectorZ(new int[] { 1, 3, 2, 10, 32, 42 },
				Concept.VorfahrtVonRechts));
		result.add(new FeatureVectorZ(new int[] { 1, 2, 5, 17, 40, 70 },
				Concept.VorfahrtVonRechts));

		result.add(new FeatureVectorZ(new int[] { 50, 0, 0, 0, 0, 0 },
				Concept.Vorfahrtsstrasse));
		result.add(new FeatureVectorZ(new int[] { 30, 1, 0, 1, 0, 1 },
				Concept.Vorfahrtsstrasse));
		result.add(new FeatureVectorZ(new int[] { 47, 0, 1, 0, 1, 0 },
				Concept.Vorfahrtsstrasse));

		result.add(new FeatureVectorZ(new int[] { 0, 0, 0, 0, 60, 30 },
				Concept.VorfahrtGewaehren));
		result.add(new FeatureVectorZ(new int[] { 0, 0, 0, 0, 70, 60 },
				Concept.VorfahrtGewaehren));
		result.add(new FeatureVectorZ(new int[] { 0, 0, 0, 0, 40, 38 },
				Concept.VorfahrtGewaehren));
		return result;
	}

	private List<FeatureVector> createRandomData() {
		List<FeatureVector> result = new LinkedList<>();

		result.addAll(createRandomVectors(Concept.LinksAbbiegen, "Links/"));
		result.addAll(createRandomVectors(Concept.RechtsAbbiegen, "Rechts/"));
		result.addAll(createRandomVectors(Concept.Stop, "Stop/"));
		result.addAll(createRandomVectors(Concept.VorfahrtGewaehren, "VorfahrtGewaehren/"));
		result.addAll(createRandomVectors(Concept.Vorfahrtsstrasse, "Vorfahrtsstrasse/"));
		result.addAll(createRandomVectors(Concept.VorfahrtVonRechts, "VorfahrtVonRechts/"));
		
		return result;
	}
	
	private List<FeatureVectorZ> createRandomVectors(Concept concept, String directory){
		List<FeatureVectorZ> result = new LinkedList<>();
		for(int i =0;i<=numberOfVectorsToCreate;i++){
			String path = getRandomPath(pathToSigns+directory);
			try {
				result.add(new FeatureVectorZ(processor.createFeatures(new File(path)),concept));
				} catch (IOException e) {
					i--;
				}
		}
		return result;
	}
	
	
	private String getRandomPath(String rootDirectory){
		String path = rootDirectory;
		path+=getRandomValueBetween(-2, 2);
		int randomDirForLevelTwo = (int)getRandomValueBetween(1,5);
		switch (randomDirForLevelTwo) {
		case 1:{path+="/"+"80x60"; break;}
		case 2:{path+="/"+"80x60 1"; break;}
		case 3:{path+="/"+"80x60 2"; break;}
		case 4:{path+="/"+"80x60 3"; break;}
		case 5:{path+="/"+"3500"; break;}
		default:{path+="/"+"80x60";
			break;
		}}
		path+="/" + "X" + getRandomValueBetween(-8, 8)*10 + "Y" + getRandomValueBetween(-8, 8)*10;
		if(randomDirForLevelTwo == 5){
			path+=".jpg";
		}else{
			path+=".bmp";
		}
		return path;
	}
	
	private long getRandomValueBetween(int low, int high) {
		return Math.round((Math.random() * (high - low) + low));
	}
	

	private void writeDataFile(List<FeatureVector> features, String name) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(
							pathToDataFile + name)));
			out.writeObject(features);
			out.close();
		} catch (Throwable t) {
			System.out.println("DataCreatorZ: Could not create " + name);
			t.printStackTrace();
		}

	}

	public static void main(String[] args) {
		if (args.length == 0) {
			new DataCreatorZ(false);
		} else {
			if (args[0].equals("dummy")) {
				new DataCreatorZ(true);
			}
		}
	}
}

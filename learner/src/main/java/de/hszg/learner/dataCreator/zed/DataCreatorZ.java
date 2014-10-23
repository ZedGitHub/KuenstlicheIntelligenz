package de.hszg.learner.dataCreator.zed;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.featureVector.zed.FeatureVectorZ;

/**
 * Creates a {@value #filename} file in {@value #pathToDataFile} containing Feature Vectors of
 * {@value #numberOfVectorsToCreate} randomly chosen files from {@value #pathToSigns}
 * To create a File {@value #dummyFilename} pass the argument "dummy".
 * @author z
 *
 */
public class DataCreatorZ {

	private static final String filename = "zdata.dat";
	private static final String dummyFilename = "zDummyData.dat";
	
	private static final String pathToSigns = "/home/z/Studium/KI/Verkehrszeichen";
	private static final String pathToDataFile = "src/main/resources/";
	
	private static final int numberOfVectorsToCreate = 1000;

	public DataCreatorZ(boolean dummy) {
		if (dummy){
		writeDataFile(createDummyData(), dummyFilename);
		}else{
			writeDataFile(createRandomData(),filename);
		}
	}

	private FeatureVectorZ[] createDummyData() {
		FeatureVectorZ[] f = new FeatureVectorZ[18];
		f[0] = new FeatureVectorZ(new int[] { 1, 1, 2, 1, 75, 80 },
				Concept.Stop);
		f[1] = new FeatureVectorZ(new int[] { 4, 2, 3, 1, 90, 85 },
				Concept.Stop);
		f[2] = new FeatureVectorZ(new int[] { 0, 0, 0, 0, 70, 70 },
				Concept.Stop);
		
		f[3] = new FeatureVectorZ(new int[] { 0, 50, 75, 0, 0, 0 },
				Concept.LinksAbbiegen);
		f[4] = new FeatureVectorZ(new int[] { 1, 20, 40, 1, 0, 1 },
				Concept.LinksAbbiegen);
		f[5] = new FeatureVectorZ(new int[] { 0, 30, 80, 0, 2, 0 },
				Concept.LinksAbbiegen);
		
		f[6] = new FeatureVectorZ(new int[] { 0, 50, 20, 0, 0, 0 },
				Concept.RechtsAbbiegen);
		f[7] = new FeatureVectorZ(new int[] { 0, 90, 70, 0, 1, 0 },
				Concept.RechtsAbbiegen);
		f[8] = new FeatureVectorZ(new int[] { 0, 76, 70, 3, 0, 2 },
				Concept.RechtsAbbiegen);
		
		f[9] = new FeatureVectorZ(new int[] { 0, 0, 0, 20, 30, 50 },
				Concept.VorfahrtVonRechts);
		f[10] = new FeatureVectorZ(new int[] { 1, 3, 2, 10, 32, 42 },
				Concept.VorfahrtVonRechts);
		f[11] = new FeatureVectorZ(new int[] { 1, 2, 5, 17, 40, 70 },
				Concept.VorfahrtVonRechts);
		
		f[12] = new FeatureVectorZ(new int[] { 50, 0, 0, 0, 0, 0 },
				Concept.Vorfahrtsstrasse);
		f[13] = new FeatureVectorZ(new int[] { 30, 1, 0, 1, 0, 1 },
				Concept.Vorfahrtsstrasse);
		f[14] = new FeatureVectorZ(new int[] { 47, 0, 1, 0, 1, 0 },
				Concept.Vorfahrtsstrasse);
		
		f[15] = new FeatureVectorZ(new int[] { 0, 0, 0, 0, 60, 30 },
				Concept.VorfahrtGewaehren);
		f[16] = new FeatureVectorZ(new int[] { 0, 0, 0, 0, 70, 60 },
				Concept.VorfahrtGewaehren);
		f[17] = new FeatureVectorZ(new int[] { 0, 0, 0, 0, 40, 38 },
				Concept.VorfahrtGewaehren);
		return f;
	}

	private FeatureVectorZ[] createRandomData() {
		FeatureVectorZ[] result = new FeatureVectorZ[numberOfVectorsToCreate];
		// TODO
		// read random images from filesystem
		// create new vectors
		return result;
	}
	
	private void writeDataFile(FeatureVectorZ[] features, String name){
		List<FeatureVector> res = new LinkedList<>();
		for (FeatureVector fv : features)
			res.add(fv);
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(pathToDataFile + name)));
			out.writeObject(res);
			out.close();
		} catch (Throwable t) {
			System.out.println("DataCreatorZ: Could not create " + name);
			t.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		if(args.length == 0){
			new DataCreatorZ(false);
		}else{
			if(args[0].equals("dummy")){
				new DataCreatorZ(true);			
			}
		}
	}
}

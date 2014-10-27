package de.hszg.learner.dataCreator.zed;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.featureVector.zed.FeatureVectorZ;

public class DataPrinter {

	public static void main(String[] args) {
		try {
			List<FeatureVectorZ> vectors = readDataFile();
			for (FeatureVectorZ featureVector : vectors) {
				System.out.println(featureVector.toString());
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static List<FeatureVectorZ> readDataFile() throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream("src/main/resources/zdata.dat")));
		List<FeatureVectorZ> result = (List<FeatureVectorZ>) in.readObject();
		in.close();
		return result;
	}

}

package de.hszg.learner.dataCreator.zed;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.featureVector.zed.FeatureVectorZ;

public class DataPrinter {

	public static void main(String[] args) {
		try {
			List<FeatureVectorZ> vectors = readDataFile();
			System.out.println("number of vectors: " + vectors.size());
			int links = 0;
			int rechts = 0;
			int vorfahrtgewahren = 0;
			int vorfahrtsstrasse = 0;
			int vorfahrtvonrechts = 0;
			int stop = 0;

			for (FeatureVectorZ featureVector : vectors) {
				if (featureVector.getConcept() == Concept.LinksAbbiegen) links++;
				if (featureVector.getConcept() == Concept.RechtsAbbiegen) rechts++;
				if (featureVector.getConcept() == Concept.Stop) stop++;
				if (featureVector.getConcept() == Concept.VorfahrtGewaehren) vorfahrtgewahren++;
				if (featureVector.getConcept() == Concept.Vorfahrtsstrasse) vorfahrtsstrasse++;
				if (featureVector.getConcept() == Concept.VorfahrtVonRechts) vorfahrtvonrechts++;


				System.out.println(featureVector.toString());
			}
			System.out.println("Left: " + links);
			System.out.println("Right: " + rechts);
			System.out.println("Vorfahrt gewahren: " + vorfahrtgewahren);
			System.out.println("vorfahrtsstrasse: " + vorfahrtsstrasse);
			System.out.println("vorfahrt von rechts: " + vorfahrtvonrechts);
			System.out.println("stop: " + stop);
			
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static List<FeatureVectorZ> readDataFile()
			throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream("src/main/resources/zdata.dat")));
		List<FeatureVectorZ> result = (List<FeatureVectorZ>) in.readObject();
		in.close();
		return result;
	}

}

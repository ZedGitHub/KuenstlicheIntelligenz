package de.hszg.learner.dataCreator.zed;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;

public class TestDataCreatorZ {

	@Test
	public void testDummyDataCreation() {
		DataCreatorZ.main(new String[] { "dummy" });
		try {
			List<FeatureVector> dummyFeatures = readDummyFile();
			assertEquals(dummyFeatures.get(0).getConcept(),Concept.Stop);
			assertEquals(dummyFeatures.get(17).getConcept(),Concept.VorfahrtGewaehren);
		} catch (ClassNotFoundException | IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}

	private List<FeatureVector> readDummyFile() throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream("src/main/resources/zDummyData.dat")));
		List<FeatureVector> result = (List<FeatureVector>) in.readObject();
		in.close();
		return result;
	}
}

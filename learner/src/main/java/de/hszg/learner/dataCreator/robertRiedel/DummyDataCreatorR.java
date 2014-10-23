package de.hszg.learner.dataCreator.robertRiedel;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.robertRiedel.FeatureVectorR;

/**
 * All the the data is accumulated here and written to disk
 * 
 * @author RobertRiedel
 *
 */
public class DummyDataCreatorR
{

	public static void main(String[] args)
	{
		new DummyDataCreatorR();
	}

	private static final String	filename	= "DummyData.dat";

	DummyDataCreatorR()
	{
		FeatureVectorR[] features = new FeatureVectorR[6];
		features[0] = new FeatureVectorR(1, 1, 1, 1, 1, 1, 4, 2, 1, Concept.Stop);
		features[1] = new FeatureVectorR(1, 1, 1, 1, 1, 1, 4, 2, 1, Concept.Stop);
		features[2] = new FeatureVectorR(1, 1, 1, 1, 1, 1, 4, 2, 1, Concept.Stop);
		features[3] = new FeatureVectorR(1, 1, 1, 1, 1, 1, 4, 2, 1, Concept.Stop);
		features[4] = new FeatureVectorR(1, 1, 1, 1, 1, 1, 4, 2, 1, Concept.Stop);
		features[5] = new FeatureVectorR(1, 1, 1, 1, 1, 1, 4, 2, 1, Concept.Stop);

		List<FeatureVectorR> res = new LinkedList<>();
		for (FeatureVectorR featureVector : features)
			res.add(featureVector);
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
			out.writeObject(res);
			out.close();
			System.out.println("done writing " + filename);
		}
		catch (Throwable t)
		{
			System.out.println("DummyDataCreator: Could not create " + filename);
			t.printStackTrace();
		}
	}

}

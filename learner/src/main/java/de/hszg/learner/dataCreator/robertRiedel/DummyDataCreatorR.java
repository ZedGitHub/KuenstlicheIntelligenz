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

	DummyDataCreatorR()
	{
		ImageProcessing ip = new ImageProcessing();
	}

}

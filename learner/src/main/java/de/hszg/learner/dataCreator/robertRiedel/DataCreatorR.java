package de.hszg.learner.dataCreator.robertRiedel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.featureVector.robertRiedel.FeatureVectorR;

/**
 * Creates a {@value #filename} file in {@value #pathToDataFile} containing Feature Vectors of {@value #numberOfVectorsToCreate} randomly chosen files from {@value #pathToSigns} To create a File
 * {@value #dummyFilename} pass the argument "dummy".
 * 
 * @author z, modified by r
 *
 */
public class DataCreatorR
{

	private static final String	filename					= "rData.dat";
	// manually set paths here
	private static final String	pathToSigns					= "/home/z/Studium/KI/Verkehrszeichen/";
	private static final String	pathToDataFile				= "src/main/resources/";

	private static final String	directoryVorfahrtVonRechts	= "VorfahrtVonRechts/";
	private static final String	directoryVorfahrtGewaehren	= "VorfahrtGewaehren/";
	private static final String	directoryStop				= "Stop/";
	private static final String	directoryRechtsAbbiegen		= "Rechts/";
	private static final String	directoryLinksAbbiegen		= "Links/";
	private static final String	directoryVorfahrtsstrasse	= "Vorfahrtsstrasse/";

	private static final int	numberOfVectorsToCreate		= 500;										// of EACH concept
	private ImageProcessorR		processor;

	public DataCreatorR()
	{
		// set arguments to determine the sectoring of the image
		this.processor = new ImageProcessorR(4, 4, 10);
		writeDataFile(createRandomData(), filename);
	}

	private List<FeatureVector> createRandomData()
	{
		List<FeatureVector> result = new LinkedList<>();

		result.addAll(createRandomVectors(Concept.LinksAbbiegen, directoryLinksAbbiegen));
		result.addAll(createRandomVectors(Concept.RechtsAbbiegen, directoryRechtsAbbiegen));
		result.addAll(createRandomVectors(Concept.Stop, directoryStop));
		result.addAll(createRandomVectors(Concept.VorfahrtGewaehren, directoryVorfahrtGewaehren));
		result.addAll(createRandomVectors(Concept.Vorfahrtsstrasse, directoryVorfahrtsstrasse));
		result.addAll(createRandomVectors(Concept.VorfahrtVonRechts, directoryVorfahrtVonRechts));

		return result;
	}

	private List<FeatureVectorR> createRandomVectors(Concept concept, String directory)
	{
		List<FeatureVectorR> result = new LinkedList<>();
		while (result.size() < numberOfVectorsToCreate)
		{
			String path = getRandomPath(pathToSigns + directory);
			try
			{
				result.add(new FeatureVectorR(processor.getFeatures(new File(path)), concept));
			}
			catch (IOException e)
			{
				System.out.println("could not read " + path);
				// ignore
			}
		}
		return result;
	}

	private String getRandomPath(String rootDirectory)
	{
		String path = rootDirectory;
		path += getRandomValueBetween(-2, 2);
		int randomDirForLevelTwo = (int) getRandomValueBetween(1, 5);
		switch (randomDirForLevelTwo)
		{
			case 1:
			{
				path += "/" + "80x60";
				break;
			}
			case 2:
			{
				path += "/" + "80x60 1";
				break;
			}
			case 3:
			{
				path += "/" + "80x60 2";
				break;
			}
			case 4:
			{
				path += "/" + "80x60 3";
				break;
			}
			case 5:
			{
				path += "/" + "3500";
				break;
			}
			default:
			{
				path += "/" + "80x60";
				break;
			}
		}
		path += "/" + "X" + getRandomValueBetween(-8, 8) * 10 + "Y" + getRandomValueBetween(-8, 8) * 10;
		if (randomDirForLevelTwo == 5)
		{
			path += ".jpg";
		}
		else
		{
			path += ".bmp";
		}
		return path;
	}

	private long getRandomValueBetween(int low, int high)
	{
		return Math.round((Math.random() * (high - low) + low));
	}

	private void writeDataFile(List<FeatureVector> features, String name)
	{
		// check if file present and create new, if so

		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(pathToDataFile + name)));
			out.writeObject(features);
			out.close();
		}
		catch (Throwable t)
		{
			System.out.println("DataCreatorR: Could not create " + name);
			t.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		new DataCreatorR();
	}
}

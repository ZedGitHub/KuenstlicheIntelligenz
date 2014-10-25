package de.hszg.learner.featureVector.robertRiedel;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;

/**
 * 
 * @author RobertRiedel
 *
 */
public class FeatureVectorR implements FeatureVector
{
	private Concept	concept;
	private int[]	feature	= new int[9];

	public FeatureVectorR(int gelb, int blauRelationLinksRechts, int blauRelationObenUnten, int rotRelationObenUnten, int rotRelationLinksRechts, int symmetrieObenUnten, int symmetrieLinksRechts, int kernFarbeWeiss, int kernFarbeSchwarz, Concept d)
	{
		feature[0] = gelb; // ob die Farbe GELB enthalten ist
		feature[1] = blauRelationObenUnten;// prozentuales Verhaeltniss BLAUer Pixel: OBEN/UNTEN
		feature[2] = blauRelationLinksRechts; // prozentuales Verhaeltniss BLAUer Pixel: LINKS/RECHTS
		feature[3] = rotRelationObenUnten; // prozentuales Verhaeltniss ROTer Pixel: OBEN/UNTEN
		feature[4] = rotRelationLinksRechts; // prozentuales Verhaeltniss ROTer Pixel: LINKS/RECHTS
		feature[5] = symmetrieObenUnten; // waagerecht spiegelbar(nach Pixelmengen)
		feature[6] = symmetrieLinksRechts; // senkrecht spiegelbar(nach Pixelmengen)
		feature[7] = kernFarbeWeiss; // Menge WEISSer Pixel im Kernbereich
		feature[8] = kernFarbeSchwarz; // Menge SCHWARZer Pixel im Kernbereich
		concept = d;
	}

	public FeatureVectorR(int[] features, Concept d)
	{
		this(features[0], features[1], features[2], features[3], features[4], features[5], features[6], features[7], features[8], d);
	}

	@Override
	public Concept getConcept()
	{
		return concept;
	}

	@Override
	public int getNumFeatures()
	{
		return feature.length;
	}

	@Override
	public int getFeatureValue(int i)
	{
		return feature[i];
	}

	public String toString()
	{
		String toString = "Feature vector: ";
		for (int i = 0; i < feature.length; i++)
		{
			toString += feature[i] + " ";
		}
		toString += concept;
		return toString;

	}

}

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

	public FeatureVectorR(int gelb, int blauRelationLinksRechts, int blauRelationObenUnten, int rotRelationObenUnten, int rotRelationLinksRechts, int symmetrieObenUnten,
			int symmetrieLinksRechts, int kernFarbeWeiss, int kernFarbeSchwarz, Concept d)
	{											//	Werte	//	Bedeutung
		feature[0] = gelb;						//	0/1		//	ob die Farbe GELB enthalten ist
		feature[1] = blauRelationObenUnten;		//	0..100	//	prozentuales Verhaeltniss BLAUer Pixel: OBEN/UNTEN
		feature[2] = blauRelationLinksRechts;	//	0..100	//	prozentuales Verhaeltniss BLAUer Pixel: LINKS/RECHTS
		feature[3] = rotRelationObenUnten;		//	0..100	//	prozentuales Verhaeltniss ROTer Pixel: OBEN/UNTEN
		feature[4] = rotRelationLinksRechts;	//	0..100	//	prozentuales Verhaeltniss ROTer Pixel: LINKS/RECHTS
		feature[5] = symmetrieObenUnten;		//	0/1		//	waagerecht spiegelbar(nach Pixelmengen)
		feature[6] = symmetrieLinksRechts;		//	0/1		//	senkrecht spiegelbar(nach Pixelmengen)
		feature[7] = kernFarbeWeiss;			//	0..100	//	Menge WEISSer Pixel im Kernbereich
		feature[8] = kernFarbeSchwarz;			//	0..100	//	Menge SCHWARZer Pixel im Kernbereich
		concept = d;
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

}

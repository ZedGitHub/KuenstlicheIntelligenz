package de.hszg.learner.featureVector.zed;

import de.hszg.learner.Concept;
import de.hszg.learner.featureVector.FeatureVector;

/**
 * A FeatureVector implementation containing the vector and the appropriate Concept
 * The Vector consists of:<p>
 * [0] % of yellow pixels | might indicate {@link Concept#Vorfahrtsstrasse}} <p>
 * [1] % of blue Pixels in the left upper corner | low percentage might indicate {@link Concept#RechtsAbbiegen} <p>
 * [2] % of blue Pixels in the right upper corner | low percentage might indicate {@link Concept#LinksAbbiegen} <p>
 * [3] % of black Pixels in the middle | might indicate {@link Concept#VorfahrtGewaehren} <p>
 * [4] % of red pixels on top | might indicate {@link Concept#VorfahrtGewaehren} <p>
 * [5] % of red pixels at the bottom | might indicate {@link Concept#VorfahrtGewaehren} <p>
 * @author z
 *
 */
public class FeatureVectorZ implements FeatureVector{
	
	private static final long serialVersionUID = 3977517987481102009L;
	private final int numberOfFeatures = 6;
	private Concept concept;
	private int[] feature = new int[numberOfFeatures];

	/**
	 * Constructor expecting an already created set of features.
	 * @param features the feature vector itself. see: {@link FeatureVectorZ}
	 * @param concept the {@link Concept}
	 */
	public FeatureVectorZ(int[] features, Concept concept) {
		this.feature = features;
		this.concept = concept;
	}
	
	@Override
	public Concept getConcept() {
		return this.concept;
	}

	@Override
	public int getNumFeatures() {
		return this.numberOfFeatures;
	}

	@Override
	public int getFeatureValue(int i) {
		return this.feature[i];
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

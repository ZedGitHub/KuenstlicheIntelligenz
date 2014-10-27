package de.hszg.learner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import de.hszg.learner.featureVector.FeatureVector;
import de.hszg.learner.featureVector.zed.FeatureVectorZ;

public class MultipleLayerArtificialNeuronalNetwork implements Learner {

	MultiLayerPerceptron perceptronNet;
	
	
	public static void main(String[] args){
		
		int[] a = new int[]{ 1,3,5,7,8,9 };
		int[] b = new int[]{ 4,3,3,7,8,9 };
		int[] c = new int[]{ 1,9,5,7,2,9 };
		int[] d = new int[]{ 1,3,12,7,34,9 };
		
		FeatureVector v1 = new FeatureVectorZ( a, Concept.LinksAbbiegen  );
		FeatureVector v2 = new FeatureVectorZ( b, Concept.VorfahrtGewaehren  );
		FeatureVector v3 = new FeatureVectorZ( c, Concept.Stop  );
		FeatureVector v4 = new FeatureVectorZ( d, Concept.RechtsAbbiegen  );
		
		Learner l = new MultipleLayerArtificialNeuronalNetwork();
		List<FeatureVector> train = new ArrayList<FeatureVector>();
		train.add(v1);
		train.add(v2);
		train.add(v3);
		train.add(v4);
		l.learn(train);
		
		Concept ca = l.classify(new FeatureVectorZ(new int[]{1,2,3,4,5,6},Concept.Unknown ));
		
		System.out.println(ca);
		
	}
	
	@Override
	public void learn(List<FeatureVector> trainingSet) {
		
		trainingSet.get(0).getNumFeatures();
		final int inputSize  = trainingSet.get(0).getNumFeatures(); 
		
		// output size is always 3 because classification
		final int outputSize = 3;
		
		// normalize training set to internally format
		DataSet trainingTuples = new DataSet(inputSize,outputSize);
		for( Iterator<FeatureVector> feature = trainingSet.iterator(); feature.hasNext(); ){
			DataSetRow inputData = normalizeInput( feature.next() );
			trainingTuples.addRow(inputData);
		}
		
		// specify the layers and their number of neurons. Each list entry is a layer and
		// the corresponding number inside the list is the number of units.
		List<Integer> neuronsInLayers = new ArrayList<Integer>();
		neuronsInLayers.add( new Integer(inputSize) );
		neuronsInLayers.add( new Integer(4));
		neuronsInLayers.add( new Integer( outputSize ) );
		
		// create the network
		TransferFunctionType funcType = TransferFunctionType.TANH;
		perceptronNet = new MultiLayerPerceptron(neuronsInLayers,funcType);
		
		// Set type of learning
		perceptronNet.setLearningRule( new BackPropagation() );
		
		// start learning
		perceptronNet.learn(trainingTuples);
		
	}

	@Override
	public Concept classify(FeatureVector example) {
		
		DataSetRow input = normalizeInput(example);
		
		perceptronNet.setInput( input.getInput() );
		
		perceptronNet.calculate();
		
		double[] output = perceptronNet.getOutput();
		
		Concept concept = doublesToConcept(output);
		
		return concept;
	}
	
	/**
	 * Preprocess input vector and map values to api intern data type.
	 * @param featureVector - The vector to map as net input and expected output.
	 * @return Api intern data type containing net input and output.
	 * */
	DataSetRow normalizeInput( FeatureVector featureVector ){
		double[] features = new double[featureVector.getNumFeatures()];
		for( int i = 0; i < featureVector.getNumFeatures(); i++ ){
			features[i] = (double)featureVector.getFeatureValue(i);
		}
		
		Concept concept = featureVector.getConcept();
		
		return new DataSetRow( features, conceptToDoubles(concept) );
	}
	
	/**
	 * Map concept to binary representation.
	 * @param concept - Concept to transform in binary vector.
	 * @return vector of size 3 containing binary representation.
	 * @note Type double was chosen to prevent type conflicts :-P
	 * */
	double[] conceptToDoubles(Concept concept){
		
		if( concept.equals( Concept.VorfahrtVonRechts ) ){
			return new double[]{0.0, 0.0, 1.0};
		}else if( concept.equals( Concept.VorfahrtGewaehren ) ){
			return new double[]{0.0 ,1.0, 0.0};
		}else if( concept.equals( Concept.Stop ) ){
			return new double[]{0.0, 1.0, 1.0};
		}else if( concept.equals( Concept.RechtsAbbiegen ) ){
			return new double[]{1.0, 0.0, 0.0};
		}else if( concept.equals( Concept.LinksAbbiegen ) ){
			return new double[]{1.0, 0.0, 1.0};
		}else if( concept.equals( Concept.Vorfahrtsstrasse ) ){
			return new double[]{1.0 ,1.0, 0.0};
		}else{
			// here it is unknown
			return new double[]{0.0,0.0,0.0};
		}
	}
	
	/**
	 * Map binary representation of size 3 to a concept.
	 * @param doubles - binary represented Concept to transform.
	 * @return The concept.
	 * @note Type double was chosen to prevent type conflicts :-P
	 * */
	Concept doublesToConcept( double[] doubles){
		if( doubles.equals( new double[]{0.0, 0.0, 1.0} ) ){
			return Concept.VorfahrtVonRechts;
		}else if( doubles.equals( new double[]{0.0, 1.0, 0.0} ) ){
			return Concept.VorfahrtGewaehren;
		}else if( doubles.equals( new double[]{0.0, 1.0, 1.0} ) ){
			return Concept.Stop;
		}else if( doubles.equals( new double[]{1.0, 0.0, 0.0} ) ){
			return Concept.RechtsAbbiegen;
		}else if( doubles.equals( new double[]{1.0, 0.0, 1.0} ) ){
			return Concept.LinksAbbiegen;
		}else if( doubles.equals( new double[]{1.0, 1.0, 0.0} ) ){
			return Concept.Vorfahrtsstrasse;
		}else {
			return Concept.Unknown;
		}
	}
	
}

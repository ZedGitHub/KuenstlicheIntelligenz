package de.hszg.learner;

import java.util.List;

import de.hszg.learner.daniel.Neuron;
import de.hszg.learner.featureVector.FeatureVector;

/**
 * Created by Daniel on 14.10.2014.
 */
public class SingleLayerArtificialNeuronalNetwork implements Learner {

    private Neuron neuronOne;
    private Neuron neuronTwo;
    private Neuron neuronThree;

    /**
     * Since a initialization of the class is needed this constructor is forbidden
     */
    private SingleLayerArtificialNeuronalNetwork(){}

    /**
     * This initializes the single layer artificial neuronal network
     *
     * @param numberOfFeatures the number of features which are in the featureVector
     */
    public SingleLayerArtificialNeuronalNetwork(int numberOfFeatures) {
        neuronOne = new Neuron(numberOfFeatures);
        neuronTwo = new Neuron(numberOfFeatures);
        neuronThree = new Neuron(numberOfFeatures);
    }

    @Override
    public void learn(List<FeatureVector> trainingSet) {
        for (int i = 0; i <= 50; i++) {
            for (FeatureVector featureVector : trainingSet) {
                boolean[] booleans = new boolean[3];

                int sumOne = neuronOne.calculateSum(featureVector);
                int sumTwo = neuronTwo.calculateSum(featureVector);
                int sumThree = neuronThree.calculateSum(featureVector);

                booleans[0] = sumOne >= 0;
                booleans[1] = sumTwo >= 0;
                booleans[2] = sumThree >= 0;

                int conceptIndex = this.calculateInt(booleans);

                if (conceptIndex > 6) {
                    conceptIndex = 0; //because 7 isn't a possible value and 0 is unknown
                }

                if (!Concept.values()[conceptIndex].equals(featureVector.getConcept())) {
                    int conceptIndexWright = featureVector.getConcept().ordinal();
                    boolean[] booleansWright = this.calculateBooleans(conceptIndexWright);

                    if(booleans[0] != booleansWright[0]) {
                        neuronOne.correctCosts(featureVector, sumOne);
                    }

                    if(booleans[1] != booleansWright[1]) {
                        neuronTwo.correctCosts(featureVector, sumTwo);
                    }

                    if(booleans[2] != booleansWright[2]) {
                        neuronThree.correctCosts(featureVector, sumThree);
                    }
                }
            }
        }
    }

    @Override
    public Concept classify(FeatureVector example) {
        boolean[] booleans = new boolean[3];

        int sumOne = neuronOne.calculateSum(example);
        int sumTwo = neuronTwo.calculateSum(example);
        int sumThree = neuronThree.calculateSum(example);

        booleans[0] = sumOne >= 0;
        booleans[1] = sumTwo >= 0;
        booleans[2] = sumThree >= 0;

        int conceptIndex = this.calculateInt(booleans);

        if (conceptIndex > 6) {
            conceptIndex = 6;
        }

        return Concept.values()[conceptIndex];
    }

    /**
     * This method converts a binary (represented by a boolean[] where boolean[0] is the 2^0 part) to a int
     *
     * @param booleans the binary which should be converted
     * @return a int representing the binary
     */
    private int calculateInt(boolean[] booleans) {
        int sum = 0;

        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                sum += Math.pow(2,i);
            }
        }

        return sum;
    }

    /**
     * This method converts a int to a binary (represented by a boolean[] where boolean[0] is the 2^0 part)
     *
     * @param conceptIndex a int which should be converted
     * @return a binary (represented by a boolean[] where boolean[0] is the 2^0 part)
     */
    private boolean[] calculateBooleans(int conceptIndex) {
        String binaryString = Integer.toBinaryString(conceptIndex);

        while (binaryString.length() < 3) {
            binaryString = "0" + binaryString;
        }

        boolean[] booleans = new boolean[binaryString.length()];

        int y = 0;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            booleans[y] = binaryString.charAt(i) == '1';
            y++;
        }

        return booleans;
    }
}

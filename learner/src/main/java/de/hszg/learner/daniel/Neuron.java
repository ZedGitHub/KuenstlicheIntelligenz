package de.hszg.learner.daniel;

import de.hszg.learner.featureVector.FeatureVector;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Daniel on 14.10.2014.
 */
public class Neuron {
    private int[] costs;
    private int threshold;

    /**
     * Initializes the neuron with random costs
     *
     * @param numberOfFeatures the number of features in a featureVector
     */
    public void initialize(int numberOfFeatures) {
        Random random = new Random();

        this.costs = new int[numberOfFeatures];

        for(int i = 0; i < numberOfFeatures; i++) {
            costs[i] = random.nextInt();
        }

        threshold = random.nextInt();
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Calculates the sum of the featureVector with the costs and the threshold
     * @param featureVector
     * @return the sum calculated by the neuron
     */
    public int calculateSum(FeatureVector featureVector) {
        int sum = 0;

        for (int i = 0; i < featureVector.getNumFeatures(); i++) {
            sum += costs[i] * featureVector.getFeatureValue(i);
        }

        return sum - threshold;
    }

    /**
     * This method corrects the costs corresponding to the learn algorithm [MinskyPapert69] of the neuron
     * @param featureVector the featureVector Which had to be calculated
     * @param sum the sum which was calculated
     */
    public void correctCosts(FeatureVector featureVector, int sum){
        if (sum >= 0) {
            for (int i = 0; i < featureVector.getNumFeatures(); i++) {
                costs[i] += featureVector.getFeatureValue(i);
            }
        } else {
            for (int i = 0; i < featureVector.getNumFeatures(); i++) {
                costs[i] -= featureVector.getFeatureValue(i);
            }
        }
    }
}

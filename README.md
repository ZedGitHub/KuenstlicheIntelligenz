KuenstlicheIntelligenz
======================
* ***SingleLayerArtificialNeuronalNetwork***: you have to use the following contstructor to initialize it `SingleLayerArtificialNeuronalNetwork(int numberOfFeatures)` where numberOfFeatures is e.g. `vectors.get(0).getNumFeatures()`

#ID3 Algorithm: learner.romano.id3.class

* ***Aehnlichkeitsmaß: Aehnlichkeitslearner.class (just initialize a new object of this learner without any parameters)

* ***MultipleLayerNeuronalNetwork*** *: use MultipleLayerArtificialNeuronalNetwork(List<Integer> unitsPerLayer), where unitsPerLayer is a list with the elements <x,5,3> and x is the number of features from the feature vector and for another instance <x,5,4,4,3> where x is also the length of the used feature vector, if you need a sample take a look inside the MultipleLayerNeuronalNetwork class there is a main method, where you can see how to use it :-)

* ***DecisionTreeCal2.class: cal2 without preprocessing the data, only initialize a object of this learner, no parameters  
* ***DecisionTreeCal2MitVorverarbeitung3Klassen.class: cal2 with preprocessing the data to 3 classes, only initialize a object of this learner, no parameters
* ***DecisionTreeCal2MitVorverarbeitung10Klassen.class: cal2 with preprocessing the data to 10 classes, only initialize a object of this learner, no parameters
package com.harel.ga.alg;


import com.harel.ga.alg.crossover.RandomOnePointCrossover;
import com.harel.ga.alg.selector.RouletteWheelSelector;

import java.util.Random;

public class AlgorithmCreator {

    public static Algorithm create(FitnessScoreCalculator fitnessScoreCalculator,
                     Mutator mutator) {
        Random random = new Random();

        PopulationReproducer populationReproducer = new PopulationReproducerImpl(new RouletteWheelSelector(random),
            new RandomOnePointCrossover(random), mutator);
        return new Algorithm(fitnessScoreCalculator, populationReproducer);
    }
}

package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Algorithm;
import com.harel.ga.alg.ChromosomeSelector;
import com.harel.ga.alg.CrossoverPerformer;
import com.harel.ga.alg.FitnessScoreCalculator;
import com.harel.ga.alg.FittestChromosomeFinder;
import com.harel.ga.alg.Mutator;
import com.harel.ga.alg.PopulationInitializer;
import com.harel.ga.alg.PopulationReproducer;
import com.harel.ga.alg.PopulationReproducerImpl;
import com.harel.ga.alg.crossover.RandomOnePointCrossover;
import com.harel.ga.alg.fittestfinder.HighFittestScoreFinder;
import com.harel.ga.alg.mutation.FlipStringGeneMutator;
import com.harel.ga.alg.selector.RouletteWheelSelector;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        String stringToMatch = "I study @ home";

        Random random = new Random();
        PopulationInitializer populationInitializer = new PopulationOfStringInitializer(stringToMatch.length(), random);
        FitnessScoreCalculator fitnessScoreCalculator = new StringFinderFitnessScoreCalculator(stringToMatch);
        FittestChromosomeFinder fittestChromosomeFinder = new HighFittestScoreFinder();

        ChromosomeSelector chromosomeSelector = new RouletteWheelSelector(random);
        CrossoverPerformer crossoverPerformer = new RandomOnePointCrossover(random);
        Mutator mutator = new FlipStringGeneMutator(random, 0.005);
        PopulationReproducer populationReproducer = new PopulationReproducerImpl(chromosomeSelector,
            crossoverPerformer,
            mutator);

        Algorithm algorithm = new Algorithm(populationInitializer,
            fitnessScoreCalculator, fittestChromosomeFinder, populationReproducer);

        algorithm.execute(500, 3500);
    }
}

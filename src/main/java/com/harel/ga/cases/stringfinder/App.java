package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Algorithm;
import com.harel.ga.alg.ChromosomeSelector;
import com.harel.ga.alg.CrossoverPerformer;
import com.harel.ga.alg.FitnessScoreCalculator;
import com.harel.ga.alg.FittestChromosomeFinder;
import com.harel.ga.alg.MutationPerformer;
import com.harel.ga.alg.PopulationInitializer;
import com.harel.ga.alg.PopulationReproducer;
import com.harel.ga.alg.PopulationReproducerImpl;
import com.harel.ga.alg.crossover.RandomOnePointCrossover;
import com.harel.ga.alg.fittestfinder.LowScoreAsFittest;
import com.harel.ga.alg.mutation.FlipStringGeneMutation;
import com.harel.ga.alg.selector.LowestScoreRouletteWheelSelector;
import com.harel.ga.alg.selector.RouletteWheelSelector;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        StringFinderContext context = new StringFinderContext("I study @ home");

        Random random = new Random();
        PopulationInitializer<StringFinderContext> populationInitializer = new PopulationOfStringInitializer(random);
        FitnessScoreCalculator<StringFinderContext> fitnessScoreCalculator = new StringFinderFitnessScoreCalculator();
        FittestChromosomeFinder fittestChromosomeFinder = new LowScoreAsFittest();

        ChromosomeSelector chromosomeSelector = new LowestScoreRouletteWheelSelector(random);
        CrossoverPerformer crossoverPerformer = new RandomOnePointCrossover(random);
        MutationPerformer mutationPerformer = new FlipStringGeneMutation(random, 0.005);
        PopulationReproducer populationReproducer = new PopulationReproducerImpl(chromosomeSelector,
            crossoverPerformer,
            mutationPerformer);

        Algorithm<StringFinderContext> algorithm = new Algorithm<>(populationInitializer,
            fitnessScoreCalculator, fittestChromosomeFinder, populationReproducer);

        algorithm.execute(context, 500, 3500);
    }
}

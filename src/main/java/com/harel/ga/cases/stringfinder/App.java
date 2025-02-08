package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Algorithm;
import com.harel.ga.alg.AlgorithmCreator;
import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.ChromosomeSelector;
import com.harel.ga.alg.CrossoverPerformer;
import com.harel.ga.alg.FitnessScoreCalculator;
import com.harel.ga.alg.Mutator;
import com.harel.ga.alg.PopulationReproducer;
import com.harel.ga.alg.PopulationReproducerImpl;
import com.harel.ga.alg.crossover.RandomOnePointCrossover;
import com.harel.ga.alg.mutation.FlipStringGeneMutator;
import com.harel.ga.alg.selector.RouletteWheelSelector;

import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        String stringToMatch = "I study @ home";

        Random random = new Random();
        List<Chromosome> population = createPopulation(stringToMatch, random);
        FitnessScoreCalculator fitnessScoreCalculator = new StringFinderFitnessScoreCalculator(stringToMatch);
        Mutator mutator = new FlipStringGeneMutator(random, 0.005);

        Algorithm algorithm = AlgorithmCreator.create(fitnessScoreCalculator, mutator);

        algorithm.execute(population, 3500);
    }

    private static List<Chromosome> createPopulation(String stringToMatch, Random random) {
        PopulationOfStringInitializer populationInitializer = new PopulationOfStringInitializer(stringToMatch.length(), random);
        return populationInitializer.init(500);
    }
}

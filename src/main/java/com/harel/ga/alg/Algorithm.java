package com.harel.ga.alg;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class Algorithm {
    private final PopulationInitializer populationInitializer;
    private final FitnessScoreCalculator fitnessScoreCalculator;
    private final PopulationReproducer populationReproducer;

    public Algorithm(PopulationInitializer populationInitializer,
                     FitnessScoreCalculator fitnessScoreCalculator,
                     PopulationReproducer populationReproducer) {
        this.populationInitializer = populationInitializer;
        this.fitnessScoreCalculator = fitnessScoreCalculator;
        this.populationReproducer = populationReproducer;
    }

    public Individual execute(int populationSize,
                              int generationCount) {
        List<Chromosome> generation = populationInitializer.init(populationSize);

        return performEvolution(generationCount, generation);
    }

    private Individual performEvolution(int generationCount,
                                        List<Chromosome> generation) {
        Individual fittestChromosome;
        int generationNumber = 0;
        do {
            List<Individual> chromosomeByFitnessScore = fitnessScoreCalculator.calc(generation);
            generation = populationReproducer.reproduce(chromosomeByFitnessScore);
            fittestChromosome = findFittestChromosome(chromosomeByFitnessScore);

            generationNumber++;
            printBestChromosomeOfGeneration(fittestChromosome, generationNumber);
        }
        while (generationNumber < generationCount && fittestChromosome.getScore() != Double.MAX_VALUE);
        return fittestChromosome;
    }

    private Individual findFittestChromosome(List<Individual> individuals) {
        return individuals.stream()
            .max(Comparator.comparingDouble(Individual::getScore))
            .orElseThrow(() -> new IllegalArgumentException("generation is empty."));

    }

    private void printBestChromosomeOfGeneration(Individual chromosome,
                                                 int generationNumber) {
        log.info("generation number={}, fitness score={}, chromosome={}", generationNumber, chromosome.getScore(), chromosome.getChromosome());
    }
}
    /*
    init first generation (population size) V
    calc fitness of created generation

    repeat until number of generations :
        * create new population upon current population and fitness score of chromosomes:
            - perform selection
            - perform crossover
            - perform mutation
        * calc fitness of new generation

     */





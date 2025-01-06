package com.harel.ga;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class Algorithm {
    private final PopulationInitializer populationInitializer;
    private final FitnessScoreCalculator fitnessScoreCalculator;
    private final FittestChromosomeFinder fittestChromosomeFinder;
    private final PopulationReproducer populationReproducer;

    public Algorithm(PopulationInitializer populationInitializer,
                     FitnessScoreCalculator fitnessScoreCalculator,
                     FittestChromosomeFinder fittestChromosomeFinder,
                     PopulationReproducer populationReproducer) {
        this.populationInitializer = populationInitializer;
        this.fitnessScoreCalculator = fitnessScoreCalculator;
        this.fittestChromosomeFinder = fittestChromosomeFinder;
        this.populationReproducer = populationReproducer;
    }

    public Chromosome execute(int populationSize,
                              int generationCount) {
        List<Chromosome> generation = populationInitializer.init(populationSize);

        return performEvolution(generationCount, generation);
    }

    private Chromosome performEvolution(int generationCount,
                                        List<Chromosome> generation) {
        Chromosome fittestChromosome;
        int generationNumber = 0;
        do {
            Map<Chromosome, Double> chromosomeByFitnessScore = fitnessScoreCalculator.calc(generation);
            generation = populationReproducer.reproduce(chromosomeByFitnessScore);
            fittestChromosome = fittestChromosomeFinder.find(chromosomeByFitnessScore);

            generationNumber++;
            printBestChromosomeOfGeneration(fittestChromosome, chromosomeByFitnessScore.get(fittestChromosome), generationNumber);
        }
        while (generationNumber < generationCount);
        return fittestChromosome;
    }

    private void printBestChromosomeOfGeneration(Chromosome chromosome,
                                                 Double score,
                                                 int generationNumber) {
        log.info("generation number={}, fitness score={}, chromosome={}", generationNumber, score, chromosome);
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





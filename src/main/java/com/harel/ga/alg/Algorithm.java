package com.harel.ga.alg;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Algorithm<CONTEXT extends AlgorithmContext> {
    private final PopulationInitializer<CONTEXT> populationInitializer;
    private final FitnessScoreCalculator<CONTEXT> fitnessScoreCalculator;
    private final FittestChromosomeFinder fittestChromosomeFinder;
    private final PopulationReproducer populationReproducer;

    public Algorithm(PopulationInitializer<CONTEXT> populationInitializer,
                     FitnessScoreCalculator<CONTEXT> fitnessScoreCalculator,
                     FittestChromosomeFinder fittestChromosomeFinder,
                     PopulationReproducer populationReproducer) {
        this.populationInitializer = populationInitializer;
        this.fitnessScoreCalculator = fitnessScoreCalculator;
        this.fittestChromosomeFinder = fittestChromosomeFinder;
        this.populationReproducer = populationReproducer;
    }

    public ChromosomeWithScore execute(CONTEXT algorithmContext,
                                       int populationSize,
                                       int generationCount) {
        List<Chromosome> generation = populationInitializer.init(algorithmContext, populationSize);

        return performEvolution(algorithmContext, generationCount, generation);
    }

    private ChromosomeWithScore performEvolution(CONTEXT algorithmContext,
                                                 int generationCount,
                                                 List<Chromosome> generation) {
        ChromosomeWithScore fittestChromosome;
        int generationNumber = 0;
        do {
            List<ChromosomeWithScore> chromosomeByFitnessScore = fitnessScoreCalculator.calc(algorithmContext, generation);
            generation = populationReproducer.reproduce(chromosomeByFitnessScore);
            fittestChromosome = fittestChromosomeFinder.find(chromosomeByFitnessScore);

            generationNumber++;
            printBestChromosomeOfGeneration(fittestChromosome, generationNumber);
        }
        while (generationNumber < generationCount && fittestChromosome.getScore() != 0.0);
        return fittestChromosome;
    }

    private void printBestChromosomeOfGeneration(ChromosomeWithScore chromosome,
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





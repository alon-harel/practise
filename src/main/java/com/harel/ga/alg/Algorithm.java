package com.harel.ga.alg;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class Algorithm {
    private final FitnessScoreCalculator fitnessScoreCalculator;
    private final PopulationReproducer populationReproducer;

    public Algorithm(FitnessScoreCalculator fitnessScoreCalculator,
                     PopulationReproducer populationReproducer) {
        this.fitnessScoreCalculator = fitnessScoreCalculator;
        this.populationReproducer = populationReproducer;
    }

    public Individual execute(List<Chromosome> chromosomes,
                              int generationCount) {
        List<Individual> population = createPopulation(chromosomes);

        return performEvolution(generationCount, population);
    }

    private Individual performEvolution(int generationCount,
                                        List<Individual> population) {
        Individual fittestIndividual = findFittestChromosome(population);

        int generationNumber = 1;
        while (generationNumber <= generationCount && fittestIndividual.getScore() != Double.MAX_VALUE) {
            List<Chromosome> newGeneration = populationReproducer.reproduce(population);
            population = createPopulation(newGeneration);
            fittestIndividual = findFittestChromosome(population);
            printBestChromosomeOfGeneration(fittestIndividual, generationNumber);

            generationNumber++;
        }

        return fittestIndividual;
    }

    private List<Individual> createPopulation(List<Chromosome> generation) {
        List<Individual> population = new ArrayList<>(generation.size());
        for (Chromosome chromosome : generation) {
            population.add(fitnessScoreCalculator.calc(chromosome));
        }

        return population;
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





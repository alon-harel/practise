package com.harel.ga.alg;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class AlgorithmTest {
    private final PopulationInitializer populationInitializer = mock(PopulationInitializer.class);
    private final FitnessScoreCalculator fitnessScoreCalculator = mock(FitnessScoreCalculator.class);
    private final PopulationReproducer populationReproducer = mock(PopulationReproducer.class);

    private final Algorithm algorithm = new Algorithm(populationInitializer,
        fitnessScoreCalculator,
        populationReproducer);

    @BeforeEach
    public void setup() {
        reset(populationInitializer, fitnessScoreCalculator, populationReproducer);
    }

    @Test
    public void returnFittestChromosome_whenThereIsOnlyOneGeneration() {
        List<Individual> generationWithScores = createGenerationWithScore();
        List<Chromosome> generation = createGenerationFrom(generationWithScores);
        Individual fittestChromosome = findFittestChromosome(generationWithScores);

        when(populationInitializer.init(generation.size())).thenReturn(generation);
        when(fitnessScoreCalculator.calc(generation)).thenReturn(generationWithScores);
        assertThat(algorithm.execute(generation.size(), 1)).isEqualTo(fittestChromosome);
    }

    private Individual findFittestChromosome(List<Individual> generationWithScores) {
        return generationWithScores.stream()
            .max(Comparator.comparingDouble(Individual::getScore))
            .orElseThrow(() -> new RuntimeException("failed to find the max score"));
    }

    private List<Chromosome> createGenerationFrom(List<Individual> generationWithScores) {
        return generationWithScores.stream()
            .map(Individual::getChromosome)
            .collect(Collectors.toList());
    }

    private List<Individual> createGenerationWithScore() {
        Individual chromosome1 = new Individual(new Chromosome(List.of(UUID.randomUUID().toString())), new Random().nextDouble());
        Individual chromosome2 = new Individual(new Chromosome(List.of(UUID.randomUUID().toString())), new Random().nextDouble());

        return List.of(chromosome1, chromosome2);
    }

    @Test
    public void returnFittestChromosome_fromSecondGeneration() {
        List<Individual> firstGenerationWithScores = createGenerationWithScore();
        List<Individual> secondGenerationWithScores = createGenerationWithScore();

        List<Chromosome> firstGeneration = createGenerationFrom(firstGenerationWithScores);
        List<Chromosome> secondGeneration = createGenerationFrom(secondGenerationWithScores);

        Individual fittestChromosomeFirstGen = findFittestChromosome(firstGenerationWithScores);
        Individual fittestChromosomeSecondGen = findFittestChromosome(secondGenerationWithScores);

        when(populationInitializer.init(firstGeneration.size())).thenReturn(firstGeneration);
        when(fitnessScoreCalculator.calc(firstGeneration)).thenReturn(firstGenerationWithScores);
        when(populationReproducer.reproduce(firstGenerationWithScores)).thenReturn(secondGeneration);
        when(fitnessScoreCalculator.calc(secondGeneration)).thenReturn(secondGenerationWithScores);

        assertThat(algorithm.execute(firstGeneration.size(), 2))
            .isEqualTo(fittestChromosomeSecondGen);
    }
}

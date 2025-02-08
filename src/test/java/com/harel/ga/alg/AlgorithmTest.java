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
    private final FittestChromosomeFinder fittestChromosomeFinder = mock(FittestChromosomeFinder.class);
    private final PopulationReproducer populationReproducer = mock(PopulationReproducer.class);

    private final Algorithm algorithm = new Algorithm(populationInitializer,
        fitnessScoreCalculator,
        fittestChromosomeFinder,
        populationReproducer);

    @BeforeEach
    public void setup() {
        reset(populationInitializer, fitnessScoreCalculator, populationReproducer, fittestChromosomeFinder);
    }

    @Test
    public void returnFittestChromosome_whenThereIsOnlyOneGeneration() {
        List<ChromosomeWithScore> generationWithScores = createGenerationWithScore();
        List<Chromosome> generation = createGenerationFrom(generationWithScores);
        ChromosomeWithScore fittestChromosome = findFittestChromosome(generationWithScores);

        when(populationInitializer.init(generation.size())).thenReturn(generation);
        when(fitnessScoreCalculator.calc(generation)).thenReturn(generationWithScores);
        when(fittestChromosomeFinder.find(generationWithScores)).thenReturn(fittestChromosome);

        assertThat(algorithm.execute(generation.size(), 1)).isEqualTo(fittestChromosome);
    }

    private ChromosomeWithScore findFittestChromosome(List<ChromosomeWithScore> generationWithScores) {
        return generationWithScores.stream()
            .max(Comparator.comparingDouble(ChromosomeWithScore::getScore))
            .orElseThrow(() -> new RuntimeException("failed to find the max score"));
    }

    private List<Chromosome> createGenerationFrom(List<ChromosomeWithScore> generationWithScores) {
        return generationWithScores.stream()
            .map(ChromosomeWithScore::getChromosome)
            .collect(Collectors.toList());
    }

    private List<ChromosomeWithScore> createGenerationWithScore() {
        ChromosomeWithScore chromosome1 = new ChromosomeWithScore(new Chromosome(List.of(UUID.randomUUID().toString())), new Random().nextDouble());
        ChromosomeWithScore chromosome2 = new ChromosomeWithScore(new Chromosome(List.of(UUID.randomUUID().toString())), new Random().nextDouble());

        return List.of(chromosome1, chromosome2);
    }

    @Test
    public void returnFittestChromosome_fromSecondGeneration() {
        List<ChromosomeWithScore> firstGenerationWithScores = createGenerationWithScore();
        List<ChromosomeWithScore> secondGenerationWithScores = createGenerationWithScore();

        List<Chromosome> firstGeneration = createGenerationFrom(firstGenerationWithScores);
        List<Chromosome> secondGeneration = createGenerationFrom(secondGenerationWithScores);

        ChromosomeWithScore fittestChromosomeFirstGen = findFittestChromosome(firstGenerationWithScores);
        ChromosomeWithScore fittestChromosomeSecondGen = findFittestChromosome(secondGenerationWithScores);

        when(populationInitializer.init(firstGeneration.size())).thenReturn(firstGeneration);
        when(fitnessScoreCalculator.calc(firstGeneration)).thenReturn(firstGenerationWithScores);
        when(fittestChromosomeFinder.find(firstGenerationWithScores)).thenReturn(fittestChromosomeFirstGen);
        when(populationReproducer.reproduce(firstGenerationWithScores)).thenReturn(secondGeneration);
        when(fitnessScoreCalculator.calc(secondGeneration)).thenReturn(secondGenerationWithScores);
        when(fittestChromosomeFinder.find(secondGenerationWithScores)).thenReturn(fittestChromosomeSecondGen);

        assertThat(algorithm.execute(firstGeneration.size(), 2))
            .isEqualTo(fittestChromosomeSecondGen);
    }
}

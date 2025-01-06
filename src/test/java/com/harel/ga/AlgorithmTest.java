package com.harel.ga;

import com.harel.ga.chromosome.AlphabeticChromosome;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Map;

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
        AlphabeticChromosome fittestChromosome = new AlphabeticChromosome(List.of("a"));
        AlphabeticChromosome anotherChromosome = new AlphabeticChromosome(List.of("b"));
        List<Chromosome> firstGeneration = List.of(fittestChromosome, anotherChromosome);
        Map<Chromosome, Double> populationWithFittestScore = Map.of(
            fittestChromosome, 100.0,
            anotherChromosome, 1.0);

        when(populationInitializer.init(firstGeneration.size())).thenReturn(firstGeneration);
        when(fitnessScoreCalculator.calc(firstGeneration)).thenReturn(populationWithFittestScore);
        when(fittestChromosomeFinder.find(populationWithFittestScore)).thenReturn(fittestChromosome);

        assertThat(algorithm.execute(firstGeneration.size(), 1)).isEqualTo(fittestChromosome);
    }

    @Test
    public void returnFittestChromosome_fromSecondGeneration() {
        AlphabeticChromosome fittestChromosomeFirstGen = new AlphabeticChromosome(List.of("a"));
        AlphabeticChromosome anotherChromosomeFirstGen = new AlphabeticChromosome(List.of("b"));
        AlphabeticChromosome fittestChromosomeSecondGen = new AlphabeticChromosome(List.of("c"));
        AlphabeticChromosome anotherChromosomeSecondGen = new AlphabeticChromosome(List.of("d"));
        List<Chromosome> firstGeneration = List.of(fittestChromosomeFirstGen, anotherChromosomeFirstGen);
        List<Chromosome> secondGeneration = List.of(fittestChromosomeSecondGen, anotherChromosomeSecondGen);
        Map<Chromosome, Double> firstGenerationWithFitnessScores = Map.of(
            fittestChromosomeFirstGen, 100.0,
            anotherChromosomeFirstGen, 1.0);
        Map<Chromosome, Double> secondGenerationWithFitnessScores = Map.of(
            fittestChromosomeSecondGen, 1000.0,
            anotherChromosomeSecondGen, 10.0);

        when(populationInitializer.init(firstGeneration.size())).thenReturn(firstGeneration);
        when(fitnessScoreCalculator.calc(firstGeneration)).thenReturn(firstGenerationWithFitnessScores);
        when(fittestChromosomeFinder.find(firstGenerationWithFitnessScores)).thenReturn(fittestChromosomeFirstGen);
        when(populationReproducer.reproduce(firstGenerationWithFitnessScores)).thenReturn(secondGeneration);
        when(fitnessScoreCalculator.calc(secondGeneration)).thenReturn(secondGenerationWithFitnessScores);
        when(fittestChromosomeFinder.find(secondGenerationWithFitnessScores)).thenReturn(fittestChromosomeSecondGen);

        assertThat(algorithm.execute(firstGeneration.size(), 2))
            .isEqualTo(fittestChromosomeSecondGen);
    }
}

package com.harel.ga.alg.selector;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.Individual;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class RouletteWheelSelectorTest {
    private final Random random = mock(Random.class);
    private final RouletteWheelSelector rouletteWheelSelector = new RouletteWheelSelector(random);

    @BeforeEach
    public void setup() {
        reset(random);
    }

    @Test
    public void whenThereIsOnlyOneElementInTheWheel_pickThisElement() {
        Individual individual = createChromosomeWithScore(50.0);
        List<Individual> generationWithSingleElement = List.of(individual);

        assertThat(rouletteWheelSelector.select(generationWithSingleElement)).isEqualTo(individual.getChromosome());
    }

    private Individual createChromosomeWithScore(double score) {
        return new Individual(new Chromosome(List.of(UUID.randomUUID().toString())), score);
    }

    @Test
    public void whenOneElementHasZeroScore_pickTheElementWithScore() {
        when(random.nextDouble()).thenReturn(new Random().nextDouble());

        Individual chromosomeWithoutScore = createChromosomeWithScore(0.0);
        Individual individual = createChromosomeWithScore(50.0);
        List<Individual> generation = List.of(chromosomeWithoutScore, individual);

        assertThat(rouletteWheelSelector.select(generation)).isEqualTo(individual.getChromosome());
    }

    @Test
    public void pickTheElement_whenTheFixedPointPointedOn() {
        Individual chromosome_range_0_50 = createChromosomeWithScore(50.0);
        Individual chromosome_range_50_75 = createChromosomeWithScore(25.0);
        Individual chromosome_range_75_175 = createChromosomeWithScore(100.0);
        List<Individual> generation = List.of(chromosome_range_0_50, chromosome_range_50_75, chromosome_range_75_175);

        verifySelectionUponFixedPoint(generation, chromosome_range_0_50.getChromosome(), 40);
        verifySelectionUponFixedPoint(generation, chromosome_range_50_75.getChromosome(), 60);
        verifySelectionUponFixedPoint(generation, chromosome_range_75_175.getChromosome(), 90);
    }

    private void verifySelectionUponFixedPoint(List<Individual> generation,
                                               Chromosome expectedPicked,
                                               double fixedPoint) {
        double scoresSum = generation.stream()
            .mapToDouble(Individual::getScore)
            .sum();

        when(random.nextDouble()).thenReturn(fixedPoint / scoresSum);
        assertThat(rouletteWheelSelector.select(generation)).isEqualTo(expectedPicked);
    }
}

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

public class LowestScoreRouletteWheelSelectorTest {

    private final Random random = mock(Random.class);
    private final LowestScoreRouletteWheelSelector selector = new LowestScoreRouletteWheelSelector(random);

    @Test
    public void whenThereIsOnlyOneElementInTheWheel_pickThisElement() {
        Individual individual = createChromosomeWithScore(50.0);
        List<Individual> generationWithSingleElement = List.of(individual);

        assertThat(selector.select(generationWithSingleElement)).isEqualTo(individual.getChromosome());
    }

    private Individual createChromosomeWithScore(double score) {
        return new Individual(new Chromosome(List.of(UUID.randomUUID().toString())), score);
    }

    @Test
    public void whenOneElementHasZeroScore_pickIt() {
        when(random.nextDouble()).thenReturn(new Random().nextDouble());

        Individual chromosomeWithZeroScore = createChromosomeWithScore(0.0);
        Individual individual = createChromosomeWithScore(50.0);
        List<Individual> generation = List.of(individual, chromosomeWithZeroScore);

        assertThat(selector.select(generation)).isEqualTo(chromosomeWithZeroScore.getChromosome());
    }

    @Test
    public void pickTheElement_whenTheFixedPointPointedOn() {
        Individual chromosome_range_0__0_2 = createChromosomeWithScore(5.0); // 1/5.0
        Individual chromosome_range_0_2__1_2 = createChromosomeWithScore(1.0); // 1/1.0
        Individual chromosome_range_1_2__1_3 = createChromosomeWithScore(10.0); // 1/10.0
        List<Individual> generation = List.of(chromosome_range_0__0_2, chromosome_range_0_2__1_2, chromosome_range_1_2__1_3);

        verifySelectionUponFixedPoint(generation, chromosome_range_0__0_2.getChromosome(), 0.01);
        verifySelectionUponFixedPoint(generation, chromosome_range_0_2__1_2.getChromosome(), 0.7);
        verifySelectionUponFixedPoint(generation, chromosome_range_1_2__1_3.getChromosome(), 1.25);
    }

    private void verifySelectionUponFixedPoint(List<Individual> generation,
                                               Chromosome expectedPicked,
                                               double fixedPoint) {
        double scoresSum = generation.stream()
            .mapToDouble(c -> 1 / c.getScore())
            .sum();

        when(random.nextDouble()).thenReturn(fixedPoint / scoresSum);
        assertThat(selector.select(generation)).isEqualTo(expectedPicked);

    }

    @BeforeEach
    public void setup() {
        reset(random);
    }

}

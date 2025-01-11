package com.harel.ga.alg.selector;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.ChromosomeWithScore;
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
        ChromosomeWithScore chromosomeWithScore = createChromosomeWithScore(50.0);
        List<ChromosomeWithScore> generationWithSingleElement = List.of(chromosomeWithScore);

        assertThat(rouletteWheelSelector.select(generationWithSingleElement)).isEqualTo(chromosomeWithScore.getChromosome());
    }

    private ChromosomeWithScore createChromosomeWithScore(double score) {
        return new ChromosomeWithScore(new Chromosome(List.of(UUID.randomUUID().toString())), score);
    }

    @Test
    public void whenOneElementHasZeroScore_pickTheElementWithScore() {
        when(random.nextDouble()).thenReturn(new Random().nextDouble());

        ChromosomeWithScore chromosomeWithoutScore = createChromosomeWithScore(0.0);
        ChromosomeWithScore chromosomeWithScore = createChromosomeWithScore(50.0);
        List<ChromosomeWithScore> generation = List.of(chromosomeWithoutScore, chromosomeWithScore);

        assertThat(rouletteWheelSelector.select(generation)).isEqualTo(chromosomeWithScore.getChromosome());
    }

    @Test
    public void pickTheElement_whenTheFixedPointPointedOn() {
        ChromosomeWithScore chromosome_range_0_50 = createChromosomeWithScore(50.0);
        ChromosomeWithScore chromosome_range_50_75 = createChromosomeWithScore(25.0);
        ChromosomeWithScore chromosome_range_75_175 = createChromosomeWithScore(100.0);
        List<ChromosomeWithScore> generation = List.of(chromosome_range_0_50, chromosome_range_50_75, chromosome_range_75_175);

        verifySelectionUponFixedPoint(generation, chromosome_range_0_50.getChromosome(), 40);
        verifySelectionUponFixedPoint(generation, chromosome_range_50_75.getChromosome(), 60);
        verifySelectionUponFixedPoint(generation, chromosome_range_75_175.getChromosome(), 90);
    }

    private void verifySelectionUponFixedPoint(List<ChromosomeWithScore> generation,
                                               Chromosome expectedPicked,
                                               double fixedPoint) {
        double scoresSum = generation.stream()
            .mapToDouble(ChromosomeWithScore::getScore)
            .sum();

        when(random.nextDouble()).thenReturn(fixedPoint / scoresSum);
        assertThat(rouletteWheelSelector.select(generation)).isEqualTo(expectedPicked);

    }

}

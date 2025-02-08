package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.Individual;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StringFinderFitnessScoreCalculatorTest {
    private final StringFinderFitnessScoreCalculator calculator =
        new StringFinderFitnessScoreCalculator("ab");

    @Test
    public void calcScoreWhenAllLettersAreChanged() {
        Chromosome chromosome = new Chromosome(List.of("g", "h"));

        assertThat(calculator.calc(List.of(chromosome)))
            .containsExactlyInAnyOrder(
                new Individual(chromosome, 500.0));
    }

    @Test
    public void scoreIsMaxIfAllLettersAreTheSame() {
        Chromosome chromosome = new Chromosome(List.of("a", "b"));

        assertThat(calculator.calc(List.of(chromosome)))
            .containsExactlyInAnyOrder(
                new Individual(chromosome, Double.MAX_VALUE));
    }
}

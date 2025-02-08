package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.ChromosomeWithScore;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StringFinderFitnessScoreCalculatorTest {
    private final StringFinderFitnessScoreCalculator calculator =
        new StringFinderFitnessScoreCalculator();

    @Test
    public void calcScoreOfAllChangedLetters() {
        StringFinderContext context = new StringFinderContext("ab");
        Chromosome chromosome = new Chromosome(List.of("g", "h"));

        assertThat(calculator.calc(context, List.of(chromosome)))
            .containsExactlyInAnyOrder(
                new ChromosomeWithScore(chromosome, 500.0));
    }

    @Test
    public void scoreIsMaxIfAllLettersAreTheSame() {
        StringFinderContext context = new StringFinderContext("ab");
        Chromosome chromosome = new Chromosome(List.of("a", "b"));

        assertThat(calculator.calc(context, List.of(chromosome)))
            .containsExactlyInAnyOrder(
                new ChromosomeWithScore(chromosome, Double.MAX_VALUE));
    }
}

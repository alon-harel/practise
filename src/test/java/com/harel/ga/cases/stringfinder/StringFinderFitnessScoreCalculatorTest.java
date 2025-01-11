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
    public void sumDistanceBetweenGenes() {
        StringFinderContext context = new StringFinderContext("ef");
        Chromosome chromosomeWithScore4 = new Chromosome(List.of("g", "h"));
        Chromosome chromosomeWithScore6 = new Chromosome(List.of("b", "c"));

        assertThat(calculator.calc(context, List.of(chromosomeWithScore4, chromosomeWithScore6)))
            .containsExactlyInAnyOrder(
                new ChromosomeWithScore(chromosomeWithScore4, 4.0),
                new ChromosomeWithScore(chromosomeWithScore6, 6.0));
    }
}

package com.harel.ga.alg.fittestfinder;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.ChromosomeWithScore;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LowScoreAsFittestTest {

    @Test
    public void returnLowestFitnessScoreChromosome() {
        ChromosomeWithScore chromosomeWithHighSore = new ChromosomeWithScore(new Chromosome(List.of("a")), 100.0);
        ChromosomeWithScore chromosomeWithLowScore = new ChromosomeWithScore(new Chromosome(List.of("b")), 1.0);

        LowScoreAsFittest lowScoreAsFittest = new LowScoreAsFittest();

        assertThat(lowScoreAsFittest.find(List.of(chromosomeWithHighSore, chromosomeWithLowScore)))
            .isEqualTo(chromosomeWithLowScore);
    }
}

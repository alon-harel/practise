package com.harel.ga.alg.selector;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.ChromosomeSelector;
import com.harel.ga.alg.ChromosomeWithScore;

import java.util.List;
import java.util.Random;

public abstract class RouletteWheelSelectorBase implements ChromosomeSelector {
    private final Random random;

    public RouletteWheelSelectorBase(Random random) {
        this.random = random;
    }

    @Override
    public Chromosome select(List<ChromosomeWithScore> chromosomeWithScores) {
        double fixedPoint = calcFixedPointUponGenerationScoresSum(chromosomeWithScores);
        return pickUponFixedPoint(chromosomeWithScores, fixedPoint);
    }

    private Chromosome pickUponFixedPoint(List<ChromosomeWithScore> chromosomeWithScores,
                                          double fixedPoint) {
        Chromosome picked = null;
        double topRange = 0.0;
        for (ChromosomeWithScore chromosomeWithScore : chromosomeWithScores) {
            topRange += chromosomeAdjustedScore(chromosomeWithScore);
            if (topRange >= fixedPoint) {
                picked = chromosomeWithScore.getChromosome();
                break;
            }
        }

        return picked;
    }

    protected abstract double chromosomeAdjustedScore(ChromosomeWithScore chromosomeWithScore);

    private double calcFixedPointUponGenerationScoresSum(List<ChromosomeWithScore> chromosomeWithScores) {
        double scoresSum = calcGenerationScoresSum(chromosomeWithScores);
        return random.nextDouble() * scoresSum;
    }

    private double calcGenerationScoresSum(List<ChromosomeWithScore> chromosomeWithScores) {
        return chromosomeWithScores.stream()
            .mapToDouble(this::chromosomeAdjustedScore)
            .sum();
    }
}

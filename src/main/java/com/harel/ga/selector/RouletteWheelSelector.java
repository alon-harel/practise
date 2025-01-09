package com.harel.ga.selector;

import com.harel.ga.Chromosome;
import com.harel.ga.ChromosomeSelector;
import com.harel.ga.ChromosomeWithScore;

import java.util.List;
import java.util.Random;

public class RouletteWheelSelector implements ChromosomeSelector {
    private final Random random;

    public RouletteWheelSelector(Random random) {
        this.random = random;
    }

    @Override
    public Chromosome select(List<ChromosomeWithScore> chromosomeWithScores) {
        double fixedPoint = calcFixedPointUponGenerationScoresSum(chromosomeWithScores);
        return pickUponFixedPoint(chromosomeWithScores, fixedPoint);
    }

    private Chromosome pickUponFixedPoint(List<ChromosomeWithScore> chromosomeWithScores, double fixedPoint) {
        Chromosome picked = null;
        double topRange = 0.0;
        for (ChromosomeWithScore chromosomeWithScore : chromosomeWithScores) {
            topRange += chromosomeWithScore.getScore();
            if (topRange >= fixedPoint) {
                picked = chromosomeWithScore.getChromosome();
                break;
            }
        }

        return picked;
    }

    private double calcFixedPointUponGenerationScoresSum(List<ChromosomeWithScore> chromosomeWithScores) {
        double scoresSum = calcGenerationScoresSum(chromosomeWithScores);
        return random.nextDouble() * scoresSum;
    }

    private double calcGenerationScoresSum(List<ChromosomeWithScore> chromosomeWithScores) {
        return chromosomeWithScores.stream()
            .mapToDouble(ChromosomeWithScore::getScore)
            .sum();
    }
}

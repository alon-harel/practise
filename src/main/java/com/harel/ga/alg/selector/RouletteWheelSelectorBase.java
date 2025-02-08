package com.harel.ga.alg.selector;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.ChromosomeSelector;
import com.harel.ga.alg.Individual;

import java.util.List;
import java.util.Random;

public abstract class RouletteWheelSelectorBase implements ChromosomeSelector {
    private final Random random;

    public RouletteWheelSelectorBase(Random random) {
        this.random = random;
    }

    @Override
    public Chromosome select(List<Individual> individuals) {
        double fixedPoint = calcFixedPointUponGenerationScoresSum(individuals);
        return pickUponFixedPoint(individuals, fixedPoint);
    }

    private Chromosome pickUponFixedPoint(List<Individual> individuals,
                                          double fixedPoint) {
        Chromosome picked = null;
        double topRange = 0.0;
        for (Individual individual : individuals) {
            topRange += chromosomeAdjustedScore(individual);
            if (topRange >= fixedPoint) {
                picked = individual.getChromosome();
                break;
            }
        }

        return picked;
    }

    protected abstract double chromosomeAdjustedScore(Individual individual);

    private double calcFixedPointUponGenerationScoresSum(List<Individual> individuals) {
        double scoresSum = calcGenerationScoresSum(individuals);
        return random.nextDouble() * scoresSum;
    }

    private double calcGenerationScoresSum(List<Individual> individuals) {
        return individuals.stream()
            .mapToDouble(this::chromosomeAdjustedScore)
            .sum();
    }
}

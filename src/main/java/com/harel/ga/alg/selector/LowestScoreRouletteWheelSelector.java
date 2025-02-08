package com.harel.ga.alg.selector;

import com.harel.ga.alg.Individual;

import java.util.Random;

public class LowestScoreRouletteWheelSelector extends RouletteWheelSelectorBase {

    public LowestScoreRouletteWheelSelector(Random random) {
        super(random);
    }

    protected double chromosomeAdjustedScore(Individual individual) {
        return (individual.getScore() != 0.0) ?
            1 / individual.getScore() : Double.POSITIVE_INFINITY;
    }
}

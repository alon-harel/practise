package com.harel.ga.alg.selector;

import com.harel.ga.alg.ChromosomeWithScore;

import java.util.Random;

public class LowestScoreRouletteWheelSelector extends RouletteWheelSelectorBase {

    public LowestScoreRouletteWheelSelector(Random random) {
        super(random);
    }

    protected double chromosomeAdjustedScore(ChromosomeWithScore chromosomeWithScore) {
        return (chromosomeWithScore.getScore() != 0.0) ?
            1 / chromosomeWithScore.getScore() : Double.POSITIVE_INFINITY;
    }
}

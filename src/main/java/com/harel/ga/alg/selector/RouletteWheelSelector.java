package com.harel.ga.alg.selector;

import com.harel.ga.alg.Individual;

import java.util.Random;

public class RouletteWheelSelector extends RouletteWheelSelectorBase {

    public RouletteWheelSelector(Random random) {
        super(random);
    }

    @Override
    protected double chromosomeAdjustedScore(Individual individual) {
        return individual.getScore();
    }
}
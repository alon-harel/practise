package com.harel.ga.alg.fittestfinder;

import com.harel.ga.alg.ChromosomeWithScore;
import com.harel.ga.alg.FittestChromosomeFinder;

import java.util.Comparator;
import java.util.List;


public class HighFittestScoreFinder implements FittestChromosomeFinder {
    @Override
    public ChromosomeWithScore find(List<ChromosomeWithScore> chromosomeWithScores) {
        return chromosomeWithScores.stream()
            .max(Comparator.comparingDouble(ChromosomeWithScore::getScore))
            .orElseThrow(() -> new IllegalArgumentException("generation is empty."));
    }
}

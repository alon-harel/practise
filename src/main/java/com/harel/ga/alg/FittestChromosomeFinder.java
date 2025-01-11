package com.harel.ga.alg;

import java.util.List;

public interface FittestChromosomeFinder {
    ChromosomeWithScore find(List<ChromosomeWithScore> chromosomeWithScores);
}

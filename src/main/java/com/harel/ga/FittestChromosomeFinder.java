package com.harel.ga;

import java.util.List;

public interface FittestChromosomeFinder {
    ChromosomeWithScore find(List<ChromosomeWithScore> chromosomeWithScores);
}

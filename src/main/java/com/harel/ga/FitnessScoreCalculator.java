package com.harel.ga;

import java.util.List;

public interface FitnessScoreCalculator {
    List<ChromosomeWithScore> calc(List<Chromosome> generation);
}

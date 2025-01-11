package com.harel.ga.alg;

import java.util.List;

public interface FitnessScoreCalculator {
    List<ChromosomeWithScore> calc(List<Chromosome> generation);
}

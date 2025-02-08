package com.harel.ga.alg;

import java.util.List;

public interface FitnessScoreCalculator {
    List<Individual> calc(List<Chromosome> generation);
}

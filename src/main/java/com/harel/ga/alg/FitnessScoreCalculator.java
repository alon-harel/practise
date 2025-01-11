package com.harel.ga.alg;

import java.util.List;

public interface FitnessScoreCalculator<ALGORITHM_CONTEXT extends AlgorithmContext> {
    List<ChromosomeWithScore> calc(ALGORITHM_CONTEXT context,
                                   List<Chromosome> generation);
}

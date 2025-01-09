package com.harel.ga;

import java.util.List;


public interface ChromosomeSelector {
    Chromosome select(List<ChromosomeWithScore> generationWithFitnessScores);
}

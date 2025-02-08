package com.harel.ga.alg;

import java.util.List;


public interface ChromosomeSelector {
    Chromosome select(List<Individual> generationWithFitnessScores);
}

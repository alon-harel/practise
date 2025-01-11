package com.harel.ga.alg;

import java.util.List;

public interface PopulationReproducer {
    List<Chromosome> reproduce(List<ChromosomeWithScore> generationWithFitnessScores);
}

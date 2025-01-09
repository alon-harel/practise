package com.harel.ga;

import java.util.List;

public interface PopulationReproducer {
    List<Chromosome> reproduce(List<ChromosomeWithScore> generationWithFitnessScores);
}

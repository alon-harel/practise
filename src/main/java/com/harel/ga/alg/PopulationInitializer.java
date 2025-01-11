package com.harel.ga.alg;

import java.util.List;

public interface PopulationInitializer<ALGORITHM_CONTEXT> {
    List<Chromosome> init(ALGORITHM_CONTEXT context,
                          int populationSize);
}

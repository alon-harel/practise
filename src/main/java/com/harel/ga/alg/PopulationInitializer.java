package com.harel.ga.alg;

import java.util.List;

public interface PopulationInitializer {
    List<Chromosome> init(int populationSize);
}

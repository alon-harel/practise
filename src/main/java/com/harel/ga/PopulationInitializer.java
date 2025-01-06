package com.harel.ga;

import java.util.List;

public interface PopulationInitializer {
    List<Chromosome> init(int populationSize);
}

package com.harel.ga;

import java.util.List;
import java.util.Map;

public interface PopulationReproducer {
    List<Chromosome> reproduce(Map<Chromosome, Double> chromosomeByFitnessScore);
}

package com.harel.ga;

import java.util.Map;

public interface ChromosomeSelector {
    Chromosome select(Map<Chromosome, Double> chromosomeByFitnessScore);
}

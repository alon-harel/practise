package com.harel.ga;

import java.util.Map;

public interface FittestChromosomeFinder {
    Chromosome find(Map<Chromosome, Double> chromosomeByFitnessScore);
}

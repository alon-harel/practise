package com.harel.ga;

import java.util.Collection;
import java.util.Map;

public interface FitnessScoreCalculator {
    Map<Chromosome, Double> calc(Collection<Chromosome> chromosomes);
}

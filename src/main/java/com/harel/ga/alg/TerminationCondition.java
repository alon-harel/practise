package com.harel.ga.alg;

public interface TerminationCondition {
    boolean shouldContinue(int generationCount,
                           int generationNumber,
                           Individual fittestIndividual);
}

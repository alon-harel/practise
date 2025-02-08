package com.harel.ga.alg.termination;

import com.harel.ga.alg.Individual;
import com.harel.ga.alg.TerminationCondition;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class FoundSolutionTerminationCondition implements TerminationCondition {

    private final double fitnessScore;

    @Override
    public boolean shouldContinue(int generationCount,
                                  int generationNumber,
                                  Individual fittestIndividual) {
        return generationNumber <= generationCount && fittestIndividual.getScore() != fitnessScore;
    }
}

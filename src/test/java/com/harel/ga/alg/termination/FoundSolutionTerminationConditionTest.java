package com.harel.ga.alg.termination;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.Individual;
import com.harel.ga.alg.TerminationCondition;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FoundSolutionTerminationConditionTest {

    private final static double FITNESS_SCORE = 100.0;
    private final FoundSolutionTerminationCondition foundSolutionTerminationCondition =
        new FoundSolutionTerminationCondition(FITNESS_SCORE);

    @Test
    public void shouldTerminate_whenNumberOfGenerationsConditionMet() {
        int generationCount = 5;
        assertThat(foundSolutionTerminationCondition.shouldContinue(generationCount, generationCount + 1, new Individual(new Chromosome(List.of(1)), FITNESS_SCORE - 10))).isFalse();
    }

    @Test
    public void shouldTerminate_whenFitnessScoreConditionMet() {
        int generationCount = 5;
        assertThat(foundSolutionTerminationCondition.shouldContinue(generationCount, generationCount - 1, new Individual(new Chromosome(List.of(1)), FITNESS_SCORE))).isFalse();
    }
}

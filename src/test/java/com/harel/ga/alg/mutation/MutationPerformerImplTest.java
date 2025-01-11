package com.harel.ga.alg.mutation;

import com.harel.ga.alg.Chromosome;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class MutationPerformerImplTest {
    private final Random random = mock(Random.class);

    @Test
    public void returnOriginalChromosome_whenMutationWasNotDrawn() {
        MutationPerformerImpl mutationPerformer = new TestMutationPerformer(random, 0.0);
        Chromosome original = new Chromosome(List.of("original"));
        assertThat(mutationPerformer.mutate(original)).isEqualTo(original);
    }

    @Test
    public void returnMutedChromosome_whenMutationWasDrawn() {
        MutationPerformerImpl mutationPerformer = new TestMutationPerformer(random, 1.0);
        Chromosome original = new Chromosome(List.of("original"));
        assertThat(mutationPerformer.mutate(original)).isEqualTo(TestMutationPerformer.MUTED_CHROMOSOME);
    }

    private static class TestMutationPerformer extends MutationPerformerImpl {

        private final static Chromosome MUTED_CHROMOSOME = new Chromosome(List.of("a"));
        public TestMutationPerformer(Random random, double mutateChances) {
            super(random, mutateChances);
        }

        @Override
        protected Chromosome doMutate(Chromosome chromosome) {
            return MUTED_CHROMOSOME;
        }
    }
}

package com.harel.ga.alg.mutation;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.MutationPerformer;

import java.util.Random;

public abstract class MutationPerformerImpl implements MutationPerformer {
    protected final Random random;
    private final double mutateChances;

    public MutationPerformerImpl(Random random, double mutateChances) {
        this.random = random;
        this.mutateChances = mutateChances;
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        if (shouldPerformMutation()) {
            return doMutate(chromosome);
        }
        return chromosome;
    }

    protected abstract Chromosome doMutate(Chromosome chromosome);

    private boolean shouldPerformMutation() {
        return this.random.nextDouble() < mutateChances;
    }
}

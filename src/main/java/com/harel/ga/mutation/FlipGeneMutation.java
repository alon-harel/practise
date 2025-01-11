package com.harel.ga.mutation;

import com.harel.ga.Chromosome;

import java.util.Random;

public abstract class FlipGeneMutation extends MutationPerformerImpl {
    public FlipGeneMutation(Random random, double mutateChances) {
        super(random, mutateChances);
    }

    @Override
    protected Chromosome doMutate(Chromosome chromosome) {
        int genePosition = pickRandomGenePosition(chromosome.getGenes().size());
        return flipGene(chromosome, genePosition);
    }

    protected abstract Chromosome flipGene(Chromosome chromosome, int genePosition);

    private int pickRandomGenePosition(int size) {
        return random.nextInt(size);
    }
}

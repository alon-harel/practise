package com.harel.ga.alg.mutation;

import com.harel.ga.alg.Chromosome;

import java.util.Random;

public class FlipStringGeneMutator extends FlipGeneMutation {
    public FlipStringGeneMutator(Random random, double mutateChances) {
        super(random, mutateChances);
    }

    @Override
    protected Chromosome flipGene(Chromosome chromosome, int genePosition) {
        System.out.println("performing mutation");
        Chromosome mutatedChromosome = new Chromosome(chromosome);
        String asciiLetter = pickRandomAsciiLetter();
        mutatedChromosome.getGenes().set(genePosition, asciiLetter);
        return mutatedChromosome;
    }

    private String pickRandomAsciiLetter() {
        int asciiFrom32To126 = random.nextInt(126 - 32 + 1) + 32;
        char charValue = (char) asciiFrom32To126;
        return Character.toString(charValue);
    }


}

package com.harel.ga.mutation;

import com.harel.ga.Chromosome;

import java.util.Random;

public class FlipStringGeneMutation extends FlipGeneMutation {
    public FlipStringGeneMutation(Random random, double mutateChances) {
        super(random, mutateChances);
    }

    @Override
    protected Chromosome flipGene(Chromosome chromosome, int genePosition) {
        Chromosome mutatedChromosome = new Chromosome(chromosome);
        String asciiLetter = pickRandomAsciiLetter();
        mutatedChromosome.getGenes().set(genePosition, asciiLetter);
        return mutatedChromosome;
    }

    private String pickRandomAsciiLetter() {
        int asciiFrom33To126 = random.nextInt(126 - 33 + 1) + 33;
        char charValue = (char) asciiFrom33To126;
        return Character.toString(charValue);
    }


}

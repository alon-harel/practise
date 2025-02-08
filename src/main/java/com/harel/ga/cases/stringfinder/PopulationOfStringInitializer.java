package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationOfStringInitializer {
    private final int stringToMatchSize;
    private final Random random;

    public PopulationOfStringInitializer(int stringToMatchSize,
                                         Random random) {
        this.stringToMatchSize = stringToMatchSize;
        this.random = random;
    }

    public List<Chromosome> init(int populationSize) {
        List<Chromosome> generation = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {
            List<Object> genes = new ArrayList<>(stringToMatchSize);
            for (int k = 0; k < stringToMatchSize; k++) {
                genes.add(pickRandomAsciiLetter());
            }
            generation.add(new Chromosome(genes));
        }
        return generation;
    }

    private Object pickRandomAsciiLetter() {
        int asciiFrom32To126 = random.nextInt(126 - 32 + 1) + 32;
        char charValue = (char) asciiFrom32To126;
        return Character.toString(charValue);
    }
}

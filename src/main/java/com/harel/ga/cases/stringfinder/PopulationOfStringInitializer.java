package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.PopulationInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationOfStringInitializer implements PopulationInitializer<StringFinderContext> {
    private final Random random;

    public PopulationOfStringInitializer(Random random) {
        this.random = random;
    }

    @Override
    public List<Chromosome> init(StringFinderContext context,
                                 int populationSize) {
        List<Chromosome> generation = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {
            List<Object> genes = new ArrayList<>(context.getStringToCalc().length());
            for (int k = 0; k < context.getStringToCalc().length(); k++) {
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

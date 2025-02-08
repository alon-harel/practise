package com.harel.ga.alg;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class PopulationReproducerImpl implements PopulationReproducer {
    private final ChromosomeSelector chromosomeSelector;
    private final CrossoverPerformer crossoverPerformer;
    private final Mutator mutator;

    public PopulationReproducerImpl(ChromosomeSelector chromosomeSelector,
                                    CrossoverPerformer crossoverPerformer,
                                    Mutator mutator) {
        this.chromosomeSelector = chromosomeSelector;
        this.crossoverPerformer = crossoverPerformer;
        this.mutator = mutator;
    }

    @Override
    public List<Chromosome> reproduce(List<ChromosomeWithScore> generationWithFitnessScores) {
        List<Chromosome> newGeneration = new ArrayList<>(generationWithFitnessScores.size());

        for (int i = 0; i < generationWithFitnessScores.size() / 2; i++) {
            Pair<Chromosome, Chromosome> offsprings = produceOffsprings(generationWithFitnessScores);
            newGeneration.add(mutator.mutate(offsprings.getLeft()));
            newGeneration.add(mutator.mutate(offsprings.getRight()));
        }

        return newGeneration;
    }

    private Pair<Chromosome, Chromosome> produceOffsprings(List<ChromosomeWithScore> generationWithFitnessScores) {
        Chromosome firstParent = chromosomeSelector.select(generationWithFitnessScores);
        Chromosome secondParent = chromosomeSelector.select(generationWithFitnessScores);

        return crossoverPerformer.perform(firstParent, secondParent);
    }
}

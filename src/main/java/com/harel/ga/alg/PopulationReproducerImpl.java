package com.harel.ga.alg;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class PopulationReproducerImpl implements PopulationReproducer {
    private final ChromosomeSelector chromosomeSelector;
    private final CrossoverPerformer crossoverPerformer;
    private final MutationPerformer mutationPerformer;

    public PopulationReproducerImpl(ChromosomeSelector chromosomeSelector,
                                    CrossoverPerformer crossoverPerformer,
                                    MutationPerformer mutationPerformer) {
        this.chromosomeSelector = chromosomeSelector;
        this.crossoverPerformer = crossoverPerformer;
        this.mutationPerformer = mutationPerformer;
    }

    @Override
    public List<Chromosome> reproduce(List<ChromosomeWithScore> generationWithFitnessScores) {
        List<Chromosome> newGeneration = new ArrayList<>(generationWithFitnessScores.size());

        for (int i = 0; i < generationWithFitnessScores.size() / 2; i++) {
            Pair<Chromosome, Chromosome> offsprings = produceOffsprings(generationWithFitnessScores);
            newGeneration.add(mutationPerformer.mutate(offsprings.getLeft()));
            newGeneration.add(mutationPerformer.mutate(offsprings.getRight()));
        }

        return newGeneration;
    }

    private Pair<Chromosome, Chromosome> produceOffsprings(List<ChromosomeWithScore> generationWithFitnessScores) {
        Chromosome firstParent = chromosomeSelector.select(generationWithFitnessScores);
        Chromosome secondParent = chromosomeSelector.select(generationWithFitnessScores);

        return crossoverPerformer.perform(firstParent, secondParent);
    }
}

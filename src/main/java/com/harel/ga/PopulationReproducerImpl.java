package com.harel.ga;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Chromosome> reproduce(Map<Chromosome, Double> chromosomeByFitnessScore) {
        List<Chromosome> newGeneration = new ArrayList<>(chromosomeByFitnessScore.size());

        for (int i = 0; i < chromosomeByFitnessScore.size() / 2; i++) {
            Pair<Chromosome, Chromosome> offsprings = produceOffsprings(chromosomeByFitnessScore);
            newGeneration.add(mutationPerformer.mutated(offsprings.getLeft()));
            newGeneration.add(mutationPerformer.mutated(offsprings.getRight()));
        }

        return newGeneration;
    }

    private Pair<Chromosome, Chromosome> produceOffsprings(Map<Chromosome, Double> chromosomeByFitnessScore) {
        Chromosome firstParent = chromosomeSelector.select(chromosomeByFitnessScore);
        Chromosome secondParent = chromosomeSelector.select(chromosomeByFitnessScore);

        return crossoverPerformer.perform(firstParent, secondParent);
    }
}

package com.harel.ga.alg;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationReproducerImplTest {

    @Test
    public void reproduceGeneration_fromCurrentGenerationAndItsFitnessScores() {
        Individual firstParent = new Individual(new Chromosome(List.of("a")), 100.0);
        Individual secondParent = new Individual(new Chromosome(List.of("b")), 1.0);
        List<Individual> generation = List.of(firstParent, secondParent);

        Chromosome firstOffspring = new Chromosome(List.of("c"));
        Chromosome secondOffspring = new Chromosome(List.of("d"));
        Chromosome firstMutatedOffspring = new Chromosome(List.of("e"));
        Chromosome secondMutatedOffspring = new Chromosome(List.of("f"));

        ChromosomeSelector chromosomeSelector = mock(ChromosomeSelector.class);
        CrossoverPerformer crossoverPerformer = mock(CrossoverPerformer.class);
        Mutator mutator = mock(Mutator.class);
        PopulationReproducerImpl populationReproducer = new PopulationReproducerImpl(chromosomeSelector,
            crossoverPerformer, mutator);

        when(chromosomeSelector.select(generation))
            .thenReturn(firstParent.getChromosome())
            .thenReturn(secondParent.getChromosome());
        when(crossoverPerformer.perform(firstParent.getChromosome(), secondParent.getChromosome())).thenReturn(Pair.of(firstOffspring, secondOffspring));
        when(mutator.mutate(firstOffspring)).thenReturn(firstMutatedOffspring);
        when(mutator.mutate(secondOffspring)).thenReturn(secondMutatedOffspring);

        assertThat(populationReproducer.reproduce(generation))
            .containsExactlyInAnyOrder(firstMutatedOffspring, secondMutatedOffspring);
    }
}

package com.harel.ga;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationReproducerImplTest {

    @Test
    public void reproduceGeneration_fromCurrentGenerationAndItsFitnessScores() {
        ChromosomeWithScore firstParent = new ChromosomeWithScore(new Chromosome(List.of("a")), 100.0);
        ChromosomeWithScore secondParent = new ChromosomeWithScore(new Chromosome(List.of("b")), 1.0);
        List<ChromosomeWithScore> generation = List.of(firstParent, secondParent);

        Chromosome firstOffspring = new Chromosome(List.of("c"));
        Chromosome secondOffspring = new Chromosome(List.of("d"));
        Chromosome firstMutatedOffspring = new Chromosome(List.of("e"));
        Chromosome secondMutatedOffspring = new Chromosome(List.of("f"));

        ChromosomeSelector chromosomeSelector = mock(ChromosomeSelector.class);
        CrossoverPerformer crossoverPerformer = mock(CrossoverPerformer.class);
        MutationPerformer mutationPerformer = mock(MutationPerformer.class);
        PopulationReproducerImpl populationReproducer = new PopulationReproducerImpl(chromosomeSelector,
            crossoverPerformer, mutationPerformer);

        when(chromosomeSelector.select(generation))
            .thenReturn(firstParent.getChromosome())
            .thenReturn(secondParent.getChromosome());
        when(crossoverPerformer.perform(firstParent.getChromosome(), secondParent.getChromosome())).thenReturn(Pair.of(firstOffspring, secondOffspring));
        when(mutationPerformer.mutate(firstOffspring)).thenReturn(firstMutatedOffspring);
        when(mutationPerformer.mutate(secondOffspring)).thenReturn(secondMutatedOffspring);

        assertThat(populationReproducer.reproduce(generation))
            .containsExactlyInAnyOrder(firstMutatedOffspring, secondMutatedOffspring);
    }
}

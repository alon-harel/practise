package com.harel.ga;

import com.harel.ga.chromosome.AlphabeticChromosome;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationReproducerImplTest {

    @Test
    public void reproduceGeneration_fromCurrentGenerationAndItsFitnessScores() {
        ChromosomeWithScore firstParent = new ChromosomeWithScore(new AlphabeticChromosome(List.of("a")), 100.0);
        ChromosomeWithScore secondParent = new ChromosomeWithScore(new AlphabeticChromosome(List.of("b")), 1.0);
        List<ChromosomeWithScore> generation = List.of(firstParent, secondParent);

        AlphabeticChromosome firstOffspring = new AlphabeticChromosome(List.of("c"));
        AlphabeticChromosome secondOffspring = new AlphabeticChromosome(List.of("d"));
        AlphabeticChromosome firstMutatedOffspring = new AlphabeticChromosome(List.of("e"));
        AlphabeticChromosome secondMutatedOffspring = new AlphabeticChromosome(List.of("f"));

        ChromosomeSelector chromosomeSelector = mock(ChromosomeSelector.class);
        CrossoverPerformer crossoverPerformer = mock(CrossoverPerformer.class);
        MutationPerformer mutationPerformer = mock(MutationPerformer.class);
        PopulationReproducerImpl populationReproducer = new PopulationReproducerImpl(chromosomeSelector,
            crossoverPerformer, mutationPerformer);

        when(chromosomeSelector.select(generation))
            .thenReturn(firstParent.getChromosome())
            .thenReturn(secondParent.getChromosome());
        when(crossoverPerformer.perform(firstParent.getChromosome(), secondParent.getChromosome())).thenReturn(Pair.of(firstOffspring, secondOffspring));
        when(mutationPerformer.mutated(firstOffspring)).thenReturn(firstMutatedOffspring);
        when(mutationPerformer.mutated(secondOffspring)).thenReturn(secondMutatedOffspring);

        assertThat(populationReproducer.reproduce(generation))
            .containsExactlyInAnyOrder(firstMutatedOffspring, secondMutatedOffspring);
    }
}

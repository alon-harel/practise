package com.harel.ga;

import com.harel.ga.chromosome.AlphabeticChromosome;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationReproducerImplTest {

    @Test
    public void reproducePopulation_fromCurrentOneAndFitnessScores() {
        AlphabeticChromosome firstParent = new AlphabeticChromosome(List.of("a"));
        AlphabeticChromosome secondParent = new AlphabeticChromosome(List.of("b"));
        Map<Chromosome, Double> chromosomeByFitnessScore = Map.of(firstParent, 10.0, secondParent, 20.0);
        AlphabeticChromosome firstOffspring = new AlphabeticChromosome(List.of("c"));
        AlphabeticChromosome secondOffspring = new AlphabeticChromosome(List.of("d"));
        AlphabeticChromosome firstMutatedOffspring = new AlphabeticChromosome(List.of("e"));
        AlphabeticChromosome secondMutatedOffspring = new AlphabeticChromosome(List.of("f"));

        ChromosomeSelector chromosomeSelector = mock(ChromosomeSelector.class);
        CrossoverPerformer crossoverPerformer = mock(CrossoverPerformer.class);
        MutationPerformer mutationPerformer = mock(MutationPerformer.class);
        PopulationReproducerImpl populationReproducer = new PopulationReproducerImpl(chromosomeSelector,
            crossoverPerformer, mutationPerformer);

        when(chromosomeSelector.select(chromosomeByFitnessScore))
            .thenReturn(firstParent)
            .thenReturn(secondParent);
        when(crossoverPerformer.perform(firstParent, secondParent)).thenReturn(Pair.of(firstOffspring, secondOffspring));
        when(mutationPerformer.mutated(firstOffspring)).thenReturn(firstMutatedOffspring);
        when(mutationPerformer.mutated(secondOffspring)).thenReturn(secondMutatedOffspring);

        assertThat(populationReproducer.reproduce(chromosomeByFitnessScore))
            .containsExactlyInAnyOrder(firstMutatedOffspring, secondMutatedOffspring);
    }
}

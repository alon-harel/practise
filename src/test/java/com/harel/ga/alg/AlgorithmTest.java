package com.harel.ga.alg;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlgorithmTest {
    private final static Chromosome FIRST_CHROMOSOME = new Chromosome(List.of(UUID.randomUUID().toString()));
    private final static Chromosome SECOND_CHROMOSOME = new Chromosome(List.of(UUID.randomUUID().toString()));

    private final static Individual INDIVIDUAL = new Individual(FIRST_CHROMOSOME, 1.0);
    private final static Individual FITTEST_INDIVIDUAL = new Individual(FIRST_CHROMOSOME, 1000.0);

    private final static List<Chromosome> CHROMOSOMES = List.of(
        FIRST_CHROMOSOME,
        SECOND_CHROMOSOME
    );

    private final FitnessScoreCalculator fitnessScoreCalculator = mock(FitnessScoreCalculator.class);
    private final TerminationCondition terminationCondition = mock(TerminationCondition.class);
    private final PopulationReproducer populationReproducer = mock(PopulationReproducer.class);

    private final Algorithm algorithm = new Algorithm(fitnessScoreCalculator,
        terminationCondition, populationReproducer);

    @Before
    public void setup() {
        reset(fitnessScoreCalculator, terminationCondition, populationReproducer);

        when(fitnessScoreCalculator.calc(FIRST_CHROMOSOME)).thenReturn(INDIVIDUAL);
        when(fitnessScoreCalculator.calc(SECOND_CHROMOSOME)).thenReturn(FITTEST_INDIVIDUAL);

        when(terminationCondition.shouldContinue(anyInt(), anyInt(), any(Individual.class)))
            .thenAnswer((Answer<Boolean>) invocation -> {
                int generataionCount = invocation.getArgument(0);
                int generationNumber = invocation.getArgument(1);
                return generataionCount >= generationNumber;
            });


        when(populationReproducer.reproduce(
            argThat((individuals -> individuals.containsAll(List.of(INDIVIDUAL, FITTEST_INDIVIDUAL)))))).thenReturn(CHROMOSOMES);
    }

    @Test
    public void returnFittestChromosome_whenThereAreNoGenerations() {
        int generationCount = 0;

        assertThat(algorithm.execute(CHROMOSOMES, generationCount)).isEqualTo(FITTEST_INDIVIDUAL);
    }

    @Test
    public void returnFittestChromosome_whenThereIsOneGenerations() {
        int generationCount = 1;
        assertThat(algorithm.execute(CHROMOSOMES, generationCount)).isEqualTo(FITTEST_INDIVIDUAL);

        verifyReproductionCount(generationCount);
    }

    private void verifyReproductionCount(int wantedNumberOfInvocations) {
        ArgumentCaptor<List<Individual>> captor = ArgumentCaptor.forClass(List.class);
        verify(populationReproducer, times(wantedNumberOfInvocations)).reproduce(captor.capture());
        List<Individual> population = captor.getValue();
        assertThat(population)
            .containsExactlyInAnyOrder(INDIVIDUAL, FITTEST_INDIVIDUAL);
    }


    @Test
    public void returnFittestChromosome_whenThereAreSeveralGenerations() {
        int generationCount = 8;

        assertThat(algorithm.execute(CHROMOSOMES, generationCount)).isEqualTo(FITTEST_INDIVIDUAL);

        verifyReproductionCount(generationCount);
    }

    @Test
    public void terminateAlgorithm_whenConditionMet() {
        int generationCount = 30;
        when(terminationCondition.shouldContinue(generationCount, 2, FITTEST_INDIVIDUAL)).thenReturn(false);

        algorithm.execute(CHROMOSOMES, generationCount);

        verifyReproductionCount(1);
    }
}

package com.harel.ga.alg.mutation;

import com.harel.ga.alg.Chromosome;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlipStringGeneMutatorTest {
    private final Random random = mock(Random.class);
    private final FlipStringGeneMutator flipStringGeneMutator = new FlipStringGeneMutator(random, 1);

    @Test
    public void flipStringGeneAtRandomPickedLocation() {
        Chromosome chromosome = new Chromosome(List.of("a", "b"));

        ensureMutationHappens();
        ensureToPickGeneAtPosition(chromosome, 1);
        ensureToPickChar('!');

        assertThat(flipStringGeneMutator.mutate(chromosome)).isEqualTo(new Chromosome(List.of("a", "!")));
    }

    private void ensureToPickChar(Character character) {
        when(random.nextInt(126 - 32 + 1)).thenReturn((int)character - 32);
    }

    private void ensureToPickGeneAtPosition(Chromosome chromosome, int selectedGenePosition) {
        when(random.nextInt(chromosome.getGenes().size())).thenReturn(selectedGenePosition);
    }

    private void ensureMutationHappens() {
        when(random.nextDouble()).thenReturn(0.5);
    }
}

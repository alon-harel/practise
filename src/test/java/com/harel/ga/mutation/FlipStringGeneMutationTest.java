package com.harel.ga.mutation;

import com.harel.ga.Chromosome;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlipStringGeneMutationTest {
    private final Random random = mock(Random.class);
    private final FlipStringGeneMutation flipStringGeneMutation = new FlipStringGeneMutation(random, 1);

    @Test
    public void flipStringGeneAtRandomPickedLocation() {
        Chromosome chromosome = new Chromosome(List.of("a", "b"));

        ensureMutationHappens();
        ensureToPickGeneAtPosition(chromosome, 1);
        ensureToPickChar('!');

        assertThat(flipStringGeneMutation.mutate(chromosome)).isEqualTo(new Chromosome(List.of("a", "!")));
    }

    private void ensureToPickChar(Character character) {
        when(random.nextInt(126 - 33 + 1)).thenReturn((int)character - 33);
    }

    private void ensureToPickGeneAtPosition(Chromosome chromosome, int selectedGenePosition) {
        when(random.nextInt(chromosome.getGenes().size())).thenReturn(selectedGenePosition);
    }

    private void ensureMutationHappens() {
        when(random.nextDouble()).thenReturn(0.5);
    }
}

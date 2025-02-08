package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Chromosome;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationOfStringInitializerTest {

    private final Random random = mock(Random.class);
    private final PopulationOfStringInitializer initializer = new PopulationOfStringInitializer(1, random);

    @Test
    public void initializePopulation() {
        ensureToPickChars('!', 'C');

        assertThat(initializer.init( 2)).containsExactlyInAnyOrder(
            new Chromosome(List.of("!")),
            new Chromosome(List.of("C")));
    }


    private void ensureToPickChars(Character firstChar,
                                   Character secondChar) {
        when(random.nextInt(126 - 32 + 1))
            .thenReturn((int)firstChar - 32)
            .thenReturn((int)secondChar - 32);
    }

}

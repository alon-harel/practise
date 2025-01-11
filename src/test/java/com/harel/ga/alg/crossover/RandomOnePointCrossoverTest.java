package com.harel.ga.alg.crossover;

import com.harel.ga.alg.Chromosome;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomOnePointCrossoverTest {
    private final Random random = mock(Random.class);
    private final RandomOnePointCrossover randomOnePointCrossover = new RandomOnePointCrossover(random);

    @Test
    public void returnOffspringsAsParents_whenCrossOverPointIs0() {
        Chromosome firstParent = new Chromosome(List.of("a", "b", "c", "d"));
        Chromosome secondParent = new Chromosome(List.of("e", "f", "g", "h"));

        when(random.nextInt(firstParent.getGenes().size())).thenReturn(0);

        Pair<Chromosome, Chromosome> offsprings = randomOnePointCrossover.perform(firstParent, secondParent);
        assertThat(offsprings.getLeft()).isEqualTo(firstParent);
        assertThat(offsprings.getRight()).isEqualTo(secondParent);
    }

    @Test
    public void replaceLsatGenInOffspring_whenCrossOverPointIsGeneSize() {
        Chromosome firstParent = new Chromosome(List.of("a", "b", "c", "d"));
        Chromosome secondParent = new Chromosome(List.of("e", "f", "g", "h"));

        when(random.nextInt(firstParent.getGenes().size())).thenReturn(firstParent.getGenes().size() - 1);

        Chromosome firstOffspring = new Chromosome(List.of("a", "b", "c", "h"));
        Chromosome secondOffspring = new Chromosome(List.of("e", "f", "g", "d"));

        Pair<Chromosome, Chromosome> offsprings = randomOnePointCrossover.perform(firstParent, secondParent);
        assertThat(offsprings.getLeft()).isEqualTo(firstOffspring);
        assertThat(offsprings.getRight()).isEqualTo(secondOffspring);
    }

    @Test
    public void replaceGensInOffspring_fromCrossOverPoint() {
        Chromosome firstParent = new Chromosome(List.of("a", "b", "c", "d"));
        Chromosome secondParent = new Chromosome(List.of("e", "f", "g", "h"));

        when(random.nextInt(firstParent.getGenes().size())).thenReturn(1);

        Chromosome firstOffspring = new Chromosome(List.of("a", "f", "g", "h"));
        Chromosome secondOffspring = new Chromosome(List.of("e", "b", "c", "d"));

        Pair<Chromosome, Chromosome> offsprings = randomOnePointCrossover.perform(firstParent, secondParent);
        assertThat(offsprings.getLeft()).isEqualTo(firstOffspring);
        assertThat(offsprings.getRight()).isEqualTo(secondOffspring);
    }
}
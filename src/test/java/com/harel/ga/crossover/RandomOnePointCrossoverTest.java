package com.harel.ga.crossover;

import com.harel.ga.Chromosome;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomOnePointCrossoverTest {

    @Test
    public void replaceFirstGenInOffspring_whenCrossOverPointIs0() {
        RandomOnePointCrossover randomOnePointCrossover = new RandomOnePointCrossover();
        Chromosome firstParent = new Chromosome(List.of("a", "b", "c"));
        Chromosome secondParent = new Chromosome(List.of("d", "e", "f"));

        Chromosome firstOffspring = new Chromosome(List.of("d", "b", "c"));
        Chromosome secondOffspring = new Chromosome(List.of("a", "e", "f"));

        Pair<Chromosome, Chromosome> offsprings = randomOnePointCrossover.perform(firstParent, secondParent);
        assertThat(offsprings.getLeft()).isEqualTo(firstOffspring);
        assertThat(offsprings.getRight()).isEqualTo(secondOffspring);
    }
}

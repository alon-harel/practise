package com.harel.ga.crossover;

import com.harel.ga.Chromosome;
import com.harel.ga.chromosome.AlphabeticChromosome;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomOnePointCrossoverTest {

    @Test
    public void replaceFirstGenInOffspring_whenCrossOverPointIs0() {
        RandomOnePointCrossover randomOnePointCrossover = new RandomOnePointCrossover();
        Chromosome firstParent = new AlphabeticChromosome(List.of("a", "b", "c"));
        Chromosome secondParent = new AlphabeticChromosome(List.of("d", "e", "f"));

        Chromosome firstOffspring = new AlphabeticChromosome(List.of("d", "b", "c"));
        Chromosome secondOffspring = new AlphabeticChromosome(List.of("a", "e", "f"));

        Pair<Chromosome, Chromosome> offsprings = randomOnePointCrossover.perform(firstParent, secondParent);
        assertThat(offsprings.getLeft()).isEqualTo(firstOffspring);
        assertThat(offsprings.getRight()).isEqualTo(secondOffspring);
    }
}

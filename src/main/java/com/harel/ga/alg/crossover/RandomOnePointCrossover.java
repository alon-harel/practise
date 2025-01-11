package com.harel.ga.alg.crossover;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.CrossoverPerformer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;

public class RandomOnePointCrossover implements CrossoverPerformer {

    private final Random random;

    public RandomOnePointCrossover(Random random) {
        this.random = random;
    }

    @Override
    public Pair<Chromosome, Chromosome> perform(Chromosome firstParent,
                                                Chromosome secondParent) {
        int crossoverPoint = pickRandomPoint(firstParent.getGenes().size());
        if (crossoverPoint != 0) {
            return replaceGenesFromCrossoverPoint(firstParent, secondParent, crossoverPoint);
        }
        return offspringsAreTheOriginalParent(firstParent, secondParent);
    }

    private Pair<Chromosome, Chromosome> offspringsAreTheOriginalParent(Chromosome firstParent, Chromosome secondParent) {
        return Pair.of(new Chromosome(firstParent), new Chromosome(secondParent));
    }

    private Pair<Chromosome, Chromosome> replaceGenesFromCrossoverPoint(Chromosome firstParent,
                                                                        Chromosome secondParent,
                                                                        int randomPoint) {
        Chromosome firstOffspring = new Chromosome(firstParent);
        Chromosome secondOffspring = new Chromosome(secondParent);
        for (int i = 0; i < firstParent.getGenes().size(); i++) {
            if (i >= randomPoint) {
                firstOffspring.getGenes().set(i, secondParent.getGenes().get(i));
                secondOffspring.getGenes().set(i, firstParent.getGenes().get(i));
            }
        }

        return Pair.of(firstOffspring, secondOffspring);
    }

    private int pickRandomPoint(int genesCount) {
        return random.nextInt(genesCount);
    }
}

package com.harel.ga.crossover;

import com.harel.ga.Chromosome;
import com.harel.ga.CrossoverPerformer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class RandomOnePointCrossover implements CrossoverPerformer {

    @Override
    public Pair<Chromosome, Chromosome> perform(Chromosome firstParent,
                                                Chromosome secondParent) {

        //Chromosome firstOffspring = new AlphabeticChromosome(List.of("d", "b", "c"));
        //Chromosome secondOffspring = new AlphabeticChromosome(List.of("a", "e", "f"));
   Chromosome firstOffspring = new Chromosome(firstParent);
   Chromosome secondOffspring = new Chromosome(secondParent);

        Object o = secondParent.getGenes().get(0);
        firstOffspring.getGenes().set(0, o);

        secondOffspring.getGenes().set(0, firstParent.getGenes().get(0));

 //firstOffspring.getGenes().set(0, secondParent.getGenes().get(0));

//        List<Object> firstOffspringGenes = new ArrayList<>();
//        List<Object> secondOffspringGenes = new ArrayList<>();
//        firstOffspringGenes.add(secondParent.getGenes().get(0));
//        for (int i = 1; i < firstParent.getGenes().size()) {
//        }

        return Pair.of(firstOffspring, secondOffspring);
    }
}

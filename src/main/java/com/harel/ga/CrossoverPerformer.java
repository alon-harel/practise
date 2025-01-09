package com.harel.ga;

import org.apache.commons.lang3.tuple.Pair;

public interface CrossoverPerformer {
    Pair<Chromosome, Chromosome> perform(Chromosome firstParent,
                                         Chromosome secondParent);
}

package com.harel.ga.alg;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@AllArgsConstructor
public class Chromosome {
    List<Object> genes;

    public Chromosome(Chromosome chromosome) {
        this.genes = new ArrayList<>(chromosome.getGenes());
    }
}

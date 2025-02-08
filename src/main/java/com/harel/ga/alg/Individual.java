package com.harel.ga.alg;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Individual {
    private final Chromosome chromosome;

    @Setter
    private double score;

    public Individual(Chromosome chromosome, double score) {
        this.chromosome = chromosome;
        this.score = score;
    }

    public Individual(Chromosome chromosome) {
        this(chromosome, Double.MIN_VALUE);
    }
}

package com.harel.ga.chromosome;

import com.harel.ga.Chromosome;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class AlphabeticChromosome implements Chromosome {
    List<String> genes;
}

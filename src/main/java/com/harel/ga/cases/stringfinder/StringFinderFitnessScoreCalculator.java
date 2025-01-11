package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.ChromosomeWithScore;
import com.harel.ga.alg.FitnessScoreCalculator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class StringFinderFitnessScoreCalculator implements FitnessScoreCalculator<StringFinderContext> {
    @Override
    public List<ChromosomeWithScore> calc(StringFinderContext context,
                                          List<Chromosome> generation) {
        List<ChromosomeWithScore> result = new ArrayList<>(generation.size());
        for (Chromosome chromosome : generation) {
            result.add(createChromosomeWithScore(context, chromosome));
        }
        return result;
    }

    private ChromosomeWithScore createChromosomeWithScore(StringFinderContext context, Chromosome chromosome) {
        double score = calcScore(context, chromosome);
        return new ChromosomeWithScore(new Chromosome(chromosome), score);
    }

    private double calcScore(StringFinderContext context, Chromosome chromosome) {
        double score = 0.0;
        for (int position = 0; position < context.getStringToCalc().length(); position++) {
            score += distanceBetweenContextToGeneAtPosition(context, chromosome, position);
        }
        return score;
    }

    private double distanceBetweenContextToGeneAtPosition(StringFinderContext context, Chromosome chromosome, int position) {
        return abs(charValueAtContext(context, position) - charValueAtGene(chromosome, position));
    }

    private double charValueAtGene(Chromosome chromosome, int position) {
        String gene = chromosome.getGenes().get(position).toString();
        return gene.charAt(0);
    }

    private double charValueAtContext(StringFinderContext context, int position) {
        return context.getStringToCalc().charAt(position);
    }
}

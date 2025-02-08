package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.Chromosome;
import com.harel.ga.alg.Individual;
import com.harel.ga.alg.FitnessScoreCalculator;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Value
public class StringFinderFitnessScoreCalculator implements FitnessScoreCalculator {
    String stringToMatch;

    @Override
    public List<Individual> calc(List<Chromosome> generation) {
        List<Individual> result = new ArrayList<>(generation.size());
        for (Chromosome chromosome : generation) {
            result.add(createChromosomeWithScore(chromosome));
        }
        return result;
    }

    private Individual createChromosomeWithScore(Chromosome chromosome) {
        double score = findNumberOfDiverseLetters(chromosome);
        score = normalize(score);
        return new Individual(new Chromosome(chromosome), score);
    }

    private double normalize(double score) {
       if (score == 0.0) {
           return Double.MAX_VALUE;
       }
       return (1 / score) * 1000;
    }

    private double findNumberOfDiverseLetters(Chromosome chromosome) {
        double score = 0.0;
        for (int position = 0; position < stringToMatch.length(); position++) {
            if (stringToMatch.charAt(position) != charValueAtGene(chromosome, position)) {
                score += 1.0;
            }
           // score += distanceBetweenContextToGeneAtPosition(context, chromosome, position);
        }
        return score;
    }

    private double distanceBetweenContextToGeneAtPosition(Chromosome chromosome, int position) {
        return abs(stringToMatch.charAt(position) - charValueAtGene(chromosome, position));
    }

    private double charValueAtGene(Chromosome chromosome, int position) {
        String gene = chromosome.getGenes().get(position).toString();
        return gene.charAt(0);
    }
}

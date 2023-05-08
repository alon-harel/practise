package com.harel.practice.tdd;

import com.harel.practise.tdd.AnagramCalculator;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

public class AnagramCalculatorTest {
    private final AnagramCalculator anagramCalculator = new AnagramCalculator();

    @Test
    public void shouldCalcAnagramForAnEmptyString() {
        assertThat(anagramCalculator.calc(""), hasSize(0));
    }

    @Test
    public void shouldCalcAnagramForASingleCharacterString() {
        assertThat(anagramCalculator.calc("a"), containsInAnyOrder("a"));
    }

    @Test
    public void shouldCalcAnagramForATwoCharactersString() {
        assertThat(anagramCalculator.calc("ab"), containsInAnyOrder("ab", "ba"));
    }

    @Test
    public void shouldCalcAnagramForAThreeCharactersString() {
        assertThat(anagramCalculator.calc("abc"), containsInAnyOrder("abc", "acb", "bac", "bca", "cab", "cba"));
    }

    @Test
    public void shouldCalcAnagramForAFiveCharactersString() {
        assertThat(new HashSet<>(anagramCalculator.calc("abcde")), hasSize(5*4*3*2));
    }
}

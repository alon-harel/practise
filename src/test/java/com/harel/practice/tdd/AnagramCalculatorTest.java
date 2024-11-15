package com.harel.practice.tdd;

import com.harel.practise.tdd.AnagramCalculator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnagramCalculatorTest {

    private final AnagramCalculator anagramCalculator = new AnagramCalculator();

    @Test
    public void returnEmptyList_whenStringIsEmpty() {
        assertThat(anagramCalculator.calc("")).isEmpty();
    }

    @Test
    public void returnAnagram_whenStringHasSingleLetter() {
        assertThat(anagramCalculator.calc("a")).containsExactlyInAnyOrder("a");
    }

    @Test
    public void returnAnagram_whenStringHasTwoLetters() {
        assertThat(anagramCalculator.calc("ab")).containsExactlyInAnyOrder("ab", "ba");
    }

    @Test
    public void returnAnagram_whenStringHasThreeLetters() {
        assertThat(anagramCalculator.calc("abc")).containsExactlyInAnyOrder(
            "abc", "acb",
            "bac", "bca",
            "cab", "cba");
    }

    @Test
    public void returnAnagram_sanity() {
        assertThat(anagramCalculator.calc("abcdefg")).hasSize(7*6*5*4*3*2);
    }
}

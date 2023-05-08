package com.harel.practise.tdd;

import java.util.ArrayList;
import java.util.List;

public class AnagramCalculator {

    public List<String> calc(String str) {
        if (str.isEmpty()) {
            return handleEmptyString();
        }
        else if (str.length() == 1) {
            return handleSingleCharactersString(str);
        }
        else if (str.length() == 2) {
            return handleTwoCharactersString(str);
        }
        return handleString(str);
    }

    private static List<String> handleTwoCharactersString(String str) {
        return List.of(str, str.substring(1) + str.charAt(0));
    }

    private static List<String> handleSingleCharactersString(String str) {
        return List.of(str);
    }

    private static List<String> handleEmptyString() {
        return List.of();
    }

    private List<String> handleString(String str) {
        List<String> anagrams = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            List<String> tmp = calc(stringWithoutCharacterAtIndex(i, str));
            for (String s : tmp) {
                anagrams.add(str.charAt(i) + s);
            }
        }

        return anagrams;
    }

    private String stringWithoutCharacterAtIndex(int index, String str) {
        return str.substring(0, index) + str.substring(index + 1);
    }
}

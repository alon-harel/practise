package com.harel.practise.tdd;

import java.util.ArrayList;
import java.util.List;

public class AnagramCalculator {

    public List<String> calc(String str) {
        if (str.isEmpty()) {
            return List.of();
        }
        if (str.length() == 1) {
            return List.of(str);
        }
        if (str.length() == 2) {
            return List.of(str, swap(str));
        }

        List<String> retVal = new ArrayList<>();
        for (int position = 0; position < str.length(); position++) {
            List<String> results = calc(stringWithoutLetterAtPosition(str, position));
            for (String s : results) {
                retVal.add(str.charAt(position) + s);
            }
        }

        return retVal;
    }

    private String stringWithoutLetterAtPosition(String str, int position) {
        return str.substring(0, position) + str.substring(position + 1);
    }

    private String swap(String str) {
        return str.substring(1) + str.charAt(0);
    }
}
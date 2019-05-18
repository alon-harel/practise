package com.harel.practise.arraysandstrings;

import java.util.HashSet;
import java.util.Set;

/*
Implement an algorithm to determine if a string has all unique characters.
What if you cannot use additional data structures?
*/
public class IsUniqueString {

    static boolean naive(String str)  {
        boolean unique = true;
        for (int index = 0; index < str.length(); index++) {
            Character letter = str.charAt(index);
            for (int innerIndex = index + 1; innerIndex < str.length(); innerIndex++) {
                Character letterToCompare = str.charAt(innerIndex);
                if (letter.equals(letterToCompare)) {
                    unique = false;
                    break;
                }
            }
            if (!unique) {
                break;
            }
        }

        return unique;
    }

    static boolean improvedWithHash(String str) {
        boolean unique = true;
        Set<Character> characters = new HashSet<>();
        for (int index = 0; index < str.length(); index++) {
            if (characters.contains(str.charAt(index))) {
                unique = false;
                break;
            }
            else {
                characters.add(str.charAt(index));
            }
        }

        return unique;
    }

    static boolean improvedWithAsciiArray(String str) {
        boolean unique = true;
        boolean[] assciiArray = new boolean[128];
        for (int index = 0; index < str.length(); index++) {
            int character = str.charAt(index);
            if (assciiArray[character]) {
                unique = false;
                break;
            }
            else {
                assciiArray[character] = true;
            }
        }

        return unique;
    }

    public static void main(String[] args) {
        System.out.println("Naive");
        String unique = "abcdefghijkl";
        System.out.println(unique + ": " + IsUniqueString.naive(unique));
        String notUnique = "abcdefghijklj";
        System.out.println(notUnique + ": " + IsUniqueString.naive(notUnique));
        System.out.println("With hash");
        System.out.println(unique + ": " + IsUniqueString.improvedWithHash(unique));
        System.out.println(notUnique + ": " + IsUniqueString.improvedWithHash(notUnique));
        System.out.println("Ascii array");
        System.out.println(unique + ": " + IsUniqueString.improvedWithAsciiArray(unique));
        System.out.println(notUnique + ": " + IsUniqueString.improvedWithAsciiArray(notUnique));
        System.out.println("No Additional Data structures is with sort and look for duplicate");
    }
}

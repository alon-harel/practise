package com.harel.practise.arraysandstrings;

public class CheckPermutation {

    static boolean isPermutationNaive(String first, String second) {
        boolean retVal = false;
        if (first.length() == second.length()) {
            retVal = true;
            for (int index = 0; index < first.length(); index++) {

//                for (int secondIndex = 0; secondIndex < second.length(); secondIndex++) {
//                    if (..)
//                }
                if (!second.contains(first.substring(index, index + 1))) {
                    retVal = false;
                    break;
                }
            }
        }

        return retVal;
    }

    static boolean isPermutationCountChars(String first, String second) {
        boolean retVal = false;
        int[] countArray = new int[128];

        if (first.length() == second.length()) {
            retVal = true;
            for (int index = 0; index < first.length(); index ++) {
                countArray[first.charAt(index)]++;
            }

            for (int index = 0; index < second.length(); index ++) {
                countArray[second.charAt(index)]--;
                if (countArray[second.charAt(index)] < 0) {
                    retVal = false;
                    break;
                }
            }
        }

        return retVal;
    }

    public static void main(String[] args) {
        String str1 = "dog";
        String str2 = "god";

        String str3 = "cake";
        String str4 = "kaef";

        System.out.println("Naive");
        System.out.println(str1 + " and " + str2 + " are permutations: " + isPermutationNaive(str1, str2));
        System.out.println(str3 + " and " + str4 + " are permutations: " + isPermutationNaive(str3, str4));
        System.out.println("Sort both and compare.");
        System.out.println("Count Chars");
        System.out.println(str1 + " and " + str2 + " are permutations: " + isPermutationNaive(str1, str2));
        System.out.println(str3 + " and " + str4 + " are permutations: " + isPermutationNaive(str3, str4));


    }
}

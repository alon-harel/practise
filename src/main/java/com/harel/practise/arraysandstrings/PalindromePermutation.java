package com.harel.practise.arraysandstrings;

public class PalindromePermutation {

    private static boolean isPalindrome(String str) {
        boolean retVal = str.length() == 1 || str.length() == 2;
        if (!retVal) {
            str = str.toLowerCase();
            int[] charArray = new int[128];
            for (int index = 0; index < str.length(); index++) {
                // NOTE: we can break here if we find more then one characters with odd appearance.
                charArray[str.charAt(index)]++;
            }

            int numberOfUnEvenCharactersAppearanceInString = 0;
            for (int index = 65; index < 123; index++) {
                // From A -> z (space = 32) representation. we will assume we don't have characters such as [, } etc.
                if (charArray[index] % 2 != 0) {
                    numberOfUnEvenCharactersAppearanceInString++;
                }
            }

            // All characters have to appear with even number.
            // We allow only one (the one in the middle to appear as odd appearance:
            retVal = (numberOfUnEvenCharactersAppearanceInString <= 1);
        }

        return retVal;
    }

    // Assume a -> z only
    // At the end, is str has even size all vector is 0.
    // If str has odd size all vector is 0 except one element which is 1.
    static boolean isPalindromeWithBitVector(String str) {
        str = str.toLowerCase();
        boolean[] vector = new boolean[Character.getNumericValue('z') - Character.getNumericValue('a')];
        for (int index = 0; index < str.length(); index++) {
            int charIndexInVector = Character.getNumericValue(str.charAt(index)) - Character.getNumericValue('a');
            vector[charIndexInVector] = !vector[charIndexInVector];
        }

        // This can look nicer...
        if (str.length() % 2 == 0) {
            boolean retVal = true;
            // All vector elements are 0:
            for (boolean value : vector) {
                if (value) {
                    retVal = false;
                }
            }
            return retVal;
        }
        else {
            int numberOfTruesInVector = 0;
            // Only one element of the vector is 1:
            for (Boolean value : vector) {
                if (value) {
                    numberOfTruesInVector++;
                }
            }

            return numberOfTruesInVector == 1;
        }
    }


    public static void main(String[] args) {
        String str = "TactCoa";
        System.out.println("String " + str + " is palindrome: " + isPalindrome(str));
        str = "tactcoapapa";
        System.out.println("String " + str + " is palindrome: " + isPalindrome(str));
        str = "TactCoab";
        System.out.println("String " + str + " is palindrome: " + isPalindrome(str));
        System.out.println("With vector");
        str = "TactCoa";
        System.out.println("String " + str + " is palindrome: " + isPalindromeWithBitVector(str));
        str = "tactcoapapa";
        System.out.println("String " + str + " is palindrome: " + isPalindromeWithBitVector(str));
        str = "TactCoab";
        System.out.println("String " + str + " is palindrome: " + isPalindromeWithBitVector(str));

    }


}

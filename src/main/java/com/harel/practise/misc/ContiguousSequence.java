package com.harel.practise.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an array of integers (both positive and negative).
 Find the contiguous sequence with the largest sum. Return the sum.
EXAMPLE
Input 2, -8, 3, -2, 4, -10
Output 5 (i.e., {3, -2, 4})

Input: 2 3 -8 -1 2 4 -2 3
Output: 7 (i.e. 2, 4, -2, 3)
 */
public class ContiguousSequence {

    public static int find(List<Integer> array) {
        // First reduce the array. Sum all positive sequences and negative sequences
        // 2 3 -8 -1 2 4 -2 3 -> 5, -9, 6, -2, 3
        // (if all negative -> return smallest negative)
        // (if all positive -> return sum of entire array)

        List<Integer> reducedArray = reduceArray(array);
        int max = 0;
        int tempMax = 0;
        for (Integer elementInArray : reducedArray) {
            tempMax = tempMax + elementInArray;
            if (tempMax > 0) {
                if (tempMax > max) {
                    // Update total max:
                    max = tempMax;
                }
            }
            else {
                // Start from 0. This sequence has got under 0:
                tempMax = 0;
            }
        }
        return max;

    }

    private static List<Integer> reduceArray(List<Integer> array) {
        List<Integer> reducedArray = new ArrayList<>();
        boolean positive = true;
        int aggregate = 0;
        for (int index = 0; index < array.size(); index++) {
            if (positive) {
                if (array.get(index) >= 0) {
                    aggregate += array.get(index);
                }
                else {
                    reducedArray.add(aggregate);
                    positive = false;
                    aggregate = array.get(index);
                }
            }
            else {
                if (array.get(index) < 0) {
                    aggregate += array.get(index);
                }
                else {
                    reducedArray.add(aggregate);
                    positive = true;
                    aggregate = array.get(index);
                }
            }
        }
        // Don't forget to add the last aggregate after existing the loop:
        reducedArray.add(aggregate);
        return reducedArray;
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(2, 3, -8, -1, 2, 4, -2, 3); // 7
        int sum = ContiguousSequence.find(array);

        System.out.println(sum);

        array = Arrays.asList(2, -8, 3, -2, 4, -10); // 5
        sum = ContiguousSequence.find(array);

        System.out.println(sum);
    }
}

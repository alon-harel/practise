package com.harel.practise.inteviews;

import java.util.HashSet;
import java.util.Set;

public class DrawKOutOfN {

    public static Set<Integer> draw(int k, int n) {
        Set<Integer> set = new HashSet<>(k);
        int[] array = new int[n];
        for (int index = 0; index < n; index++) {
            array[index] = index;
        }

        for (int index = 0; index < k; index++) {
            // draw a number:
            Double drawed = Math.random() * n;
            int drawAsInt = drawed.intValue();
            set.add(array[drawAsInt]);

            n--;
            // Swap the draw number and put it in the end of the list so it will not be draw again:
            int temp = array[n];
            array[n] = array[drawAsInt];
            array[drawAsInt] = temp;
        }

        return set;
    }

    public static void main(String[] args) {
        final Set<Integer> draw = draw(18, 20);
        System.out.println("The set has 18 elements as expected: " + draw.size());
        System.out.println(draw);
    }
}

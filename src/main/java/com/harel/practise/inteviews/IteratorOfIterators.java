package com.harel.practise.inteviews;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/*
given n sorted iterators, write an iterator that will implements:
hasNext() - iterator has next element
next() - suppply the next smallest object from all iterators.

Example:
iter1 - 1, 2, 5, 7, 8, 9...
iter2 - 1, 1, 3, 4, 7, 10...
iter3 - 0, 1, 5, 6, 8, 11...

result when invoking next() -
0, 1, 1, 1, 1, 2, 3, 4, 5, 5, 6, 7, 7, 8, 8, 9...
 */

public class IteratorOfIterators implements Iterator<Integer> {
    private final Map<Iterator<Integer>, Integer> iteratorToCurrentValue;

    public IteratorOfIterators(List<Iterator<Integer>> iterators) {
        this.iteratorToCurrentValue = iterators.stream()
            .collect(Collectors.toMap(Function.identity(), Iterator::next));
    }

    @Override
    public boolean hasNext() {
        return !iteratorToCurrentValue.isEmpty();
    }

    @Override
    public Integer next() {
        if (iteratorToCurrentValue.isEmpty()) {
            return null;
        }

        // Get the smallest iterator value:
        int smallestValue = Integer.MAX_VALUE;
        Iterator<Integer> iteratorWithSmallestValue = null;
        for (Map.Entry<Iterator<Integer>, Integer> entry : iteratorToCurrentValue.entrySet()) {
            if (entry.getValue() < smallestValue) {
                smallestValue = entry.getValue();
                iteratorWithSmallestValue = entry.getKey();
            }
        }
        if (iteratorWithSmallestValue != null) {
            // Put the next iterator item:
            if (iteratorWithSmallestValue.hasNext()) {
                iteratorToCurrentValue.put(iteratorWithSmallestValue, iteratorWithSmallestValue.next());
            }
            else {
                // This iterator has no more elements, remove it from the map:
                iteratorToCurrentValue.remove(iteratorWithSmallestValue);
            }
        }

        return smallestValue;
    }

    public static void main(String[] args) {
        Iterator<Integer> iter1 = asList(2, 5, 7, 8, 9).iterator();
        Iterator<Integer> iter2 = asList(1, 1, 3, 4, 7, 11).iterator();
        Iterator<Integer> iter3 = asList(0, 0, 0, 1, 5, 6, 8, 11, 12).iterator();
        Iterator<Integer> iter4 = singletonList(100).iterator();

        IteratorOfIterators iteratorOfIterators = new IteratorOfIterators(asList(iter1, iter2, iter3, iter4));
        while (iteratorOfIterators.hasNext()) {
            System.out.println(iteratorOfIterators.next());
        }
    }
}

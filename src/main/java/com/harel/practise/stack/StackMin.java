package com.harel.practise.stack;


import java.util.ArrayList;

/*
How would you design a stack which, in addition to push and pop, has a function min
which returns the minimum element. Push, pop and min should all operate in 0(1) time
 */
public class StackMin {

    ArrayList<Integer> stack = new ArrayList<>();
    ArrayList<Integer> minStack = new ArrayList<>();

    StackMin push(Integer value) {
        stack.add(value);
        if (minStack.isEmpty()) {
            minStack.add(value);
        }
        else {
            if (minStack.get(minStack.size() - 1) >= value) {
                // This is the new smallest value. Should be in the top of the minStack:
                minStack.add(value);
            }
        }

        return this;
    }

    Integer pop() {
        Integer value = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        if (minStack.get(minStack.size() - 1) == value) {
            // Remove this min value from the min stack too:
            minStack.remove(minStack.size() - 1);
        }

        return value;
    }

    Integer min() {
        return minStack.get(minStack.size() - 1);
    }

    public static void main(String[] args) {
        StackMin stackMin = new StackMin();
        stackMin.push(3)
                .push(4)
                .push(2)
                .push(4)
                .push(3)
                .push(1)
                .push(1)
                .push(2);

        System.out.println(stackMin.min()); // 1

        stackMin.pop(); // pop 2
        System.out.println(stackMin.min()); // 1

        stackMin.pop(); // pop 1
        System.out.println(stackMin.min()); // 1

        stackMin.pop(); // pop 1
        System.out.println(stackMin.min()); // 2

        stackMin.pop(); // pop 3
        System.out.println(stackMin.min()); // 2

        stackMin.pop(); // pop 4
        System.out.println(stackMin.min()); // 2

        stackMin.pop(); // pop 2
        System.out.println(stackMin.min()); // 3
    }
}

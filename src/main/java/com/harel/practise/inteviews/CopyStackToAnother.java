package com.harel.practise.inteviews;

import java.util.Stack;

/*
Move elements from one stack to another keeping the stack order and using O(1) memory as buffer
 */
public class CopyStackToAnother {

    public static Stack<Integer> move(Stack<Integer> source) {
        Stack<Integer> target = new Stack<>();
        Integer buffer;
        int stackSize = source.size();

        for (int index = 0; index < stackSize; index++) {
            // Move all elements from source to target:
            while (!source.isEmpty()) {
                target.push(source.pop());
            }

            // Keep the last element in the buffer:
            buffer = target.pop();

            // Move back the elements to the source (without the elements that are already sorted):
            for (int secondIndex = index + 1; secondIndex < stackSize; secondIndex++) {
                source.push(target.pop());
            }

            target.push(buffer);
        }

        return target;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);

        System.out.println(stack);
        System.out.println("----------------------");
        System.out.println(move(stack));
    }
}

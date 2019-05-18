package com.harel.practise.linkedlist;

/*
You have two numbers represented by a linked list, where each node contains a single digit.
The digits are stored in reverse order, such that the 1's digit is at the head of the list.
Write a function that adds the two numbers and returns the sum as a linked list.
EXAMPLE
Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .That is,617 + 295. Output:2 -> 1 -> 9.That is,912.
 */
public class SumLists {

    static LinkedListNode<Integer> sum(LinkedListNode<Integer> node1, LinkedListNode<Integer> node2) {
        Integer current = node1.getData() + node2.getData();
        int carry = 0;
        if (current > 10) {
            carry = 1;
            current = current - 10;
        }
        LinkedListNode<Integer> headSumNode = new LinkedListNode<>(current);

        sum(headSumNode, node1.getNext(), node2.getNext(), carry);

        return headSumNode;
    }

    private static void sum(LinkedListNode<Integer> sumNode,
                            LinkedListNode<Integer> node1,
                            LinkedListNode<Integer> node2,
                            int carry) {
        int sum = carry;
        if (node1 != null) {
            sum += node1.getData();
            node1 = node1.getNext();
        }
        if (node2 != null) {
            sum += node2.getData();
            node2 = node2.getNext();
        }
        if (sum > 10) {
            carry = 1;
            sum = sum - 10;
        }
        else {
            carry = 0;
        }

        LinkedListNode<Integer> nextSum = sumNode.append(sum);
        if ((node1 != null) || (node2 != null)) {
            // Continue to next node:
            sum(nextSum, node1, node2, carry);
        }
    }

    public static void main(String[] args) {
        //Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .That is,617 + 295. Output:2 -> 1 -> 9.That is,912.
        LinkedListNode<Integer> node1 = new LinkedListNode<>(7);
        node1.append(1).append(6);

        LinkedListNode<Integer> node2 = new LinkedListNode<>(5);
        node2.append(9).append(2);

      //  LinkedListNode<Integer> sum = sum(node1, node2);
        //System.out.println(sum);

        node1 = new LinkedListNode<>(0);
        node1.append(7).append(1).append(6); // 6170
        node2 = new LinkedListNode<>(5);
        node2.append(9).append(2); // 295
        // 6170 + 295 = 6465
        LinkedListNode sum = sum(node1, node2);
        System.out.println(sum);
    }
}

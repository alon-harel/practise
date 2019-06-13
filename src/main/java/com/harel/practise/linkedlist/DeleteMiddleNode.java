package com.harel.practise.linkedlist;

/*
Implement an algorithm to delete a node in the middle (Le., any node but the first and last node,
 not necessarily the exact middle) of a singly linked list, given only access to that node.
EXAMPLE
Input: the node c from the linked list a- >b- >c - >d - >e- >f
Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 */
public class DeleteMiddleNode {

    public static <T> void delete(LinkedListNode<T> nodeInTheMiddle) {
        // C data has D as data
        // C points to E
        nodeInTheMiddle.setData(nodeInTheMiddle.getNext().getData());
        nodeInTheMiddle.setNext(nodeInTheMiddle.getNext().getNext());
    }

    public static void main(String[] args) {
        LinkedListNode<String> node = new LinkedListNode<>("a");
        node.append("b").append("c").append("d").append("e").append("f");
        System.out.println(node.toString());

        System.out.println("----------------------------");
        DeleteMiddleNode.delete(node.getNext().getNext());
        System.out.println(node.toString());
    }
}

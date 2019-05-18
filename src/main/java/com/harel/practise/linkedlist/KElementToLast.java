package com.harel.practise.linkedlist;

// Implement an algorithm to find the kth to last element of a singly linked list.
public class KElementToLast {

    // Send a pointer k steps forward and then move both pointers (k and the head) till the k pointer reaches
    // the end od the list.
    public static LinkedListNode find(LinkedListNode head, int k) {
        LinkedListNode node = head;
        for (int index = 0; index < k; index++) {
            node = node.getNext();
            if (node == null) {
                break;
            }
        }
        if (node == null) {
            return null;
        }
        else if(node.getNext() == null) {
            return head;
        }
        else {
            while (node != null) {
                node = node.getNext();
                head = head.getNext();
            }

            return head;
        }
    }

    public static void main(String[] args) {
        LinkedListNode<Integer> node = new LinkedListNode<>(1);
        node.append(2).append(3).append(4).append(5);

        LinkedListNode kNode = KElementToLast.find(node, 1);
        System.out.println(kNode != null ? kNode.getData() : "null");

        kNode = KElementToLast.find(node, 2);
        System.out.println(kNode != null ? kNode.getData() : "null");

        kNode = KElementToLast.find(node, 3);
        System.out.println(kNode != null ? kNode.getData() : "null");

        kNode = KElementToLast.find(node, 4);
        System.out.println(kNode != null ? kNode.getData() : "null");

        kNode = KElementToLast.find(node, 5);
        System.out.println(kNode != null ? kNode.getData() : "null");

        kNode = KElementToLast.find(node, 6);
        System.out.println(kNode != null ? kNode.getData() : "null");


    }
}

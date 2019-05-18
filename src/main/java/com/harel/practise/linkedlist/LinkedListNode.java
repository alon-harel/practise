package com.harel.practise.linkedlist;


import java.util.HashSet;
import java.util.Set;

/*
Remove Dups: Write code to remove duplicates from an unsorted linked list. FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
 */
public class LinkedListNode<T> {
    private T data;
    private LinkedListNode next;

    public LinkedListNode(T data) {
        this.data = data;
    }

    public LinkedListNode(T data, LinkedListNode next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public LinkedListNode<T> append(T value) {
        LinkedListNode<T> next = new LinkedListNode<>(value);
        this.next = next;

        return next;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append(data);
        LinkedListNode iterator = this;
        while (iterator.next != null) {
            builder.append(", ").append(iterator.next.data);
            iterator = iterator.next;
        }

        return builder.toString();
    }

    public void removeDuplication() {
        Set<Object> duplicates = new HashSet<>();
        LinkedListNode iterator = this;
        LinkedListNode previous = null;

        while (iterator != null) {
            if (!duplicates.contains(iterator.data)) {
                duplicates.add(iterator.data);
                // This is not duplication. Previous becomes this, the iterator move to next
                previous = iterator;
                iterator = iterator.next;

            }
            else {
                // Duplication. Previous next is not this but this's next. the move to the next.
                previous.next = iterator.next;
                iterator = iterator.next;
            }
        }
    }

    // This has not additional memory but it is O(2^n)...
    public void removeDuplicationWithoutHashset() {
        LinkedListNode current = this;
        while (current != null) {

            LinkedListNode previous = current;
            LinkedListNode iterator = current.next;
            while (iterator != null) {
                if (!iterator.data.equals(current.data)) {
                    previous = iterator;
                    iterator = iterator.next;
                }
                else {
                    previous.next = iterator.next;
                    iterator = iterator.next;
                }
            }

            current = current.next;
        }

    }

    public static void main(String[] args) {
        LinkedListNode<Integer> node = new LinkedListNode<>(1);
        node.append(2).append(3).append(4).append(5);
        node.removeDuplication();
        System.out.println(node);

        node = new LinkedListNode<>(1);
        node.append(2).append(2).append(3).append(4);
        node.removeDuplication();
        System.out.println(node);

        node = new LinkedListNode<>(1);
        node.append(2).append(2).append(2).append(2).append(2).append(2).append(2).append(2).append(3);
        node.removeDuplication();
        System.out.println(node);

        node = new LinkedListNode<>(1);
        node.removeDuplication();
        System.out.println(node);


        // Without Hash:
        System.out.println("-----------------------------");

        node = new LinkedListNode<>(1);
        node.append(2).append(3).append(4).append(5);
        node.removeDuplicationWithoutHashset();
        System.out.println(node);

        node = new LinkedListNode<>(1);
        node.append(2).append(2).append(3).append(4);
        node.removeDuplicationWithoutHashset();
        System.out.println(node);

        node = new LinkedListNode<>(1);
        node.append(2).append(2).append(2).append(2).append(2).append(2).append(2).append(2).append(3);
        node.removeDuplicationWithoutHashset();
        System.out.println(node);

        node = new LinkedListNode<>(1);
        node.removeDuplicationWithoutHashset();
        System.out.println(node);

    }

}

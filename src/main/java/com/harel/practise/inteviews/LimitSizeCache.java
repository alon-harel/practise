package com.harel.practise.inteviews;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
Create cache with a limit size.
When the cache is full, remove from the cache the least used element.
*/
public class LimitSizeCache {
    private LinkedList<DoublyLinkedList> queue = new LinkedList<>();  // Replace this with doubly linked list
    private final Map<String, DoublyLinkedList> cache = new HashMap<>();

    // The least used element (first element to be removed in case cache is full):
    private DoublyLinkedList tail;
    // The Element that was used lately:
    private DoublyLinkedList head;

    private final int cacheSize;

    public LimitSizeCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String key : cache.keySet()) {
            builder.append(key).append(", ");
        }

        return builder.toString();
    }


    String get(String key) {
        DoublyLinkedList node = cache.get(key);

        if (node != head) {
            // Set the node we get to be the new head:
            node.next = head;
            head.prev = node;

            // In case this node was the tail, set a new tail:
            if (node == tail) {
                tail = node.prev;
            }
            // The node is now the head and does not have previous node before it:
            node.prev = null;
        }


        return node.getValue();
    }


    void put(String value) {
        DoublyLinkedList node = new DoublyLinkedList(value);

        if (cache.size() == cacheSize) {
            DoublyLinkedList nodeToDelete = tail;
            // Remove the tail, and set it to one node before it:
            tail = tail.prev;
            cache.remove(nodeToDelete.getValue());
        }

        cache.put(value, node);
        // This is the first element that we add, the tail and head points at the same node:
        if (tail == null && head == null) {
            tail = node;
            head = node;
        }
        else {
            // The new node becomes the head. The old head is "pushed" forward. its previous node
            // is the new head.
            node.next = head;
            head.setPrev(node);
            head = node;
        }
    }

    static class DoublyLinkedList {
        String value;
        DoublyLinkedList prev;
        DoublyLinkedList next;

        public DoublyLinkedList(String value) {
            this.value = value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public DoublyLinkedList getPrev() {
            return prev;
        }

        public void setPrev(DoublyLinkedList prev) {
            this.prev = prev;
        }

        public DoublyLinkedList getNext() {
            return next;
        }

        public void setNext(DoublyLinkedList next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LimitSizeCache limitSizeCache = new LimitSizeCache(3);
        limitSizeCache.put("1");
        limitSizeCache.put("2");
        limitSizeCache.put("3");
        limitSizeCache.put("4");

        // 1 is the last used and should be out:
        System.out.println(limitSizeCache);

        limitSizeCache = new LimitSizeCache(3);
        limitSizeCache.put("1");
        limitSizeCache.put("2");
        limitSizeCache.put("3");
        limitSizeCache.get("1");
        limitSizeCache.get("2");
        limitSizeCache.get("2");
        limitSizeCache.get("1");

        limitSizeCache.put("4");

        // 3 is the last used and should be out:
        System.out.println(limitSizeCache);

        limitSizeCache = new LimitSizeCache(3);
        limitSizeCache.put("1");
        limitSizeCache.put("2");
        limitSizeCache.put("3");
        limitSizeCache.get("1");
        limitSizeCache.get("1");
        limitSizeCache.get("1");
        limitSizeCache.get("3");

        limitSizeCache.put("4");

        // 2 is the last used and should be out:
        System.out.println(limitSizeCache);
    }


}

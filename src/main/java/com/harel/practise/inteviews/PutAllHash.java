package com.harel.practise.inteviews;

/*
 Implement a class with the following behaviour:
 1. put(key1, X)
 2. put(key2, Y)
 3. get(key1) -> X
 4. putAll(Z) // Replace all keys with the value Z
 5. get(key1) -> Z
 6. get(key2) -> Z
 7. get(ke3) -> null
 8. put(key1, X)
 9. get(key1) -> X
 10. get(key2) -> Z
 11. get(ke3) -> null
 */

/*
A master value in addition to the map.
Insert the items with time stamp to see if to take master or not.
 */
import java.util.HashMap;
import java.util.Map;

public class PutAllHash {

    class Item {
        Integer value;
        long timeStamp;

        public Item(Integer value) {
            this.value = value;
            this.timeStamp = System.currentTimeMillis();
            try {
                // Some delay so times will differ
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Item masterValue = null;
    private final Map<Integer, Item> map = new HashMap<>();

    void put(Integer key, Integer value) {
        map.put(key, new Item(value));

    }

    void putAll(Integer value) {
        masterValue = new Item(value);
    }

    Integer get(Integer key) {
        Item item = map.get(key);
        if (item != null) {
            if (masterValue != null) {
                if (item.timeStamp < masterValue.timeStamp) {
                    item = masterValue;
                }
            }
        }
        return item != null ? item.value : null;
    }

    public static void main(String[] args) {
        PutAllHash putAllHash = new PutAllHash();
        putAllHash.put(1, 1);
        putAllHash.put(2, 2);
        System.out.println(putAllHash.get(1));
        System.out.println(putAllHash.get(2));
        System.out.println(putAllHash.get(3)); // -> null
        putAllHash.putAll(100); // Default key is now 100
        System.out.println(putAllHash.get(1));
        System.out.println(putAllHash.get(2));
        putAllHash.put(1, 1); // Override key = 1
        System.out.println(putAllHash.get(1));
        System.out.println(putAllHash.get(2)); // stay 100
        System.out.println(putAllHash.get(3));
        // Result: 1, 2, null, 100, 100, 1, 100, null
    }
}
package com.harel.practise.inteviews;

/*
 Implement a class with the following behaviour:
 1. put(key1, X)
 2. put(key2, Y)
 3. get(key1) -> X
 4. putAll(Z) // Replace all keys with the value Z
 5. get(key1) -> Z
 6. get(key2) -> Z
 7. put(key1, X)
 8. get(key1) -> X
 (. get(key2) -> Z
 */

import java.util.HashMap;
import java.util.Map;

public class PutAllHash {

    private Integer masterValue = null;
    private Map<Integer, Integer> map = new HashMap<>();

    void put(Integer key, Integer value) {
        map.put(key, value);
    }

    void putAll(Integer value) {
        masterValue = value;
        map.clear();
    }

    Integer get(Integer key) {
        Integer retVal = masterValue;
        if (map.containsKey(key)) {
            retVal = map.get(key);
        }

        return retVal;
    }

    public static void main(String[] args) {
        PutAllHash putAllHash = new PutAllHash();
        putAllHash.put(1, 1);
        putAllHash.put(2, 2);
        System.out.println(putAllHash.get(1));
        System.out.println(putAllHash.get(2));
        putAllHash.putAll(100); // Default key is now 100
        System.out.println(putAllHash.get(1));
        System.out.println(putAllHash.get(2));
        putAllHash.put(1, 1); // Override key = 1
        System.out.println(putAllHash.get(1));
        System.out.println(putAllHash.get(2));
        // Result: 1, 2, 100, 100, 1, 100
    }
}

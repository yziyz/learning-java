package yz.learning.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * JDK8中Map.forEach方法
 */
public class MapForeach {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("two", 2);
        map.put("one", 1);
        map.put("three", 3);
        map.forEach((key, value) -> {
            System.out.printf("k: %s, v: %d\n", key, value);
        });
    }
}

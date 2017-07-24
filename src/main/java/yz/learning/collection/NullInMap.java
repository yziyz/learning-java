package yz.learning.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map中的空值
 */
public class NullInMap {
    public static void main(String[] args) {
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put(null, 0);//java.lang.NullPointerException
        hashtable.put("null", null);//java.lang.NullPointerException

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(null, 1);//java.lang.NullPointerException
        concurrentHashMap.put("null", null);//java.lang.NullPointerException

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("null", null);
        treeMap.put(null, 1);//java.lang.NullPointerException

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(null, 1);
        hashMap.put("null", null);
        hashMap.put(null, null);
    }
}

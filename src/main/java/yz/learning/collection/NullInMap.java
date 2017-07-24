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
        /*
        集合初始化时，指定集合初始值大小。initialCapacity = (需要存储的元素个数 / 负载因子) + 1。注意负载因子（即 loader factor）默认为 0.75，如果暂时无法确定初始值大小，请设置为 16。
         */
        Hashtable<String, Integer> hashtable = new Hashtable<>(16);
        hashtable.put(null, 0);//java.lang.NullPointerException
        hashtable.put("null", null);//java.lang.NullPointerException

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>(16);
        concurrentHashMap.put(null, 1);//java.lang.NullPointerException
        concurrentHashMap.put("null", null);//java.lang.NullPointerException

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("null", null);
        treeMap.put(null, 1);//java.lang.NullPointerException

        HashMap<String, Integer> hashMap = new HashMap<>(16);
        hashMap.put(null, 1);
        hashMap.put("null", null);
        hashMap.put(null, null);
    }
}

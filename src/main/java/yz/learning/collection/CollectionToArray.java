package yz.learning.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合转数组
 */
public class CollectionToArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("a");
        list.add("b");
        String[] array = new String[list.size()];
        array = list.toArray(array);
        for (String s : array) {
            System.out.println(s);
        }
    }
}

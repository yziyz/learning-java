package yz.learning.collection;

import java.util.Arrays;
import java.util.List;

/**
 * 数组转集合
 */
public class ArrayToCollection {
    public static void main(String[] args) {
        String[] array = new String[] {"a", "b"};
        //数组转集合
        List<String> list = Arrays.asList(array);
        for (String s : list) {
            System.out.println(s);
        }
    }
}

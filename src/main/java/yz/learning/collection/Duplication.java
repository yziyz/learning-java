package yz.learning.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 袁臻
 * 2017/09/06 15:32
 */
public class Duplication {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("abc");
        list.add("abc");
        System.out.println("list:");
        list.forEach(System.out::println);

        Set<String> set = new HashSet<>(2);
        set.add("123");
        set.add("123");
        System.out.println("set:");
        set.forEach(System.out::println);
    }
}

package yz.learning.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 在循环中删除元素
 */
public class RemoveEntryInLoop {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        /*
        for (String s : list) {
            if ("1".equals(s)) {
                list.remove(s);//java.util.ConcurrentModificationException
            }
        }
         */


        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if ("1".equals(s)) {
                iterator.remove();
            }
        }
        /*
        等同于如下lambda表达：
        list.removeIf("2"::equals);
         */

        list.forEach(System.out::println);
    }
}

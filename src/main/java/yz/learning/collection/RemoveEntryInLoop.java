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
        /*for (String s : list) {
            if ("1".equals(s)) {
                list.remove(s);
            }
        }
        Exception in thread "main" java.util.ConcurrentModificationException
            at java.util.LinkedList$ListItr.checkForComodification(LinkedList.java:966)
            at java.util.LinkedList$ListItr.next(LinkedList.java:888)
            at yz.learning.collection.RemoveEntryInLoop.main(RemoveEntryInLoop.java:16)
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

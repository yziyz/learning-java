package yz.learning.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 测试Wildcards
 */
public class Wildcards {
    public static void main(String[] args) {
        List<?> c = new ArrayList<String>();
        //Error:(12, 15) java: incompatible types: java.lang.Object cannot be converted to capture#1 of ?
        //c.add(new Object());

    }

    void printCollection(Collection<?> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }
}

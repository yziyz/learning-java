package yz.learning.basic;

import java.util.Objects;

/**
 * Integer对象比较
 */
public class ObjectEquals {
    public static void main(String[] args) {
        /*
        说明：对于Integer var = ? 在-128 至 127 范围内的赋值，Integer 对象是在 IntegerCache.cache 产生，会复用已有对象，
        这个区间内的 Integer 值可以直接使用==进行判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，
        推荐使用equals方法进行判断。
         */
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
        System.out.println(Objects.equals(a, b));

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);
        System.out.println(Objects.equals(c, d));
    }
}

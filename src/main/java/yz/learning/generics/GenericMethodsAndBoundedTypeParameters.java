package yz.learning.generics;

/**
 * 范型方法和有界类型参数
 */
public class GenericMethodsAndBoundedTypeParameters {
    private interface Comparable<T> {
        int compareTo(T o);
    }

    //有界类型参数是遗传算法实现的关键
    public static <T extends Comparable> int countGreaterThan(T[] array, T t) {
        int count = 0;
        for (T e : array) {
            if (e.compareTo(t) > 0) {
                count++;
            }
        }
        return count;
    }
}
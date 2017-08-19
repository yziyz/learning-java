package yz.learning.regex;

/**
 * @author 袁臻
 * 2017/08/03 16:42
 */
public class StringSplit {
    public static void main(String[] args) {
        String string = "一.二.三.四.五";
        String[] segments = string.split("\\.");
        System.out.println("数组长度" + segments.length);
        for (String s : segments) {
            System.out.println(s);
        }
    }
}

package yz.learning.basic;

import java.util.Objects;

/**
 * @author 袁臻
 * 2017-07-28 13:09
 */
public class DetermineNull {
    public static void main(String[] args) {
        //新建非空字符串string
        final String notNullString = "Hello World!";
        final String nullString = null;
        //判断string是否为null
        //System.out.println(nullString.equals(notNullString));//此处抛出NPE
        System.out.println(Objects.equals(nullString, notNullString));//此处没有NPE，false
        //System.out.println(nullString.equals(null));//此处抛出NPE
        System.out.println(Objects.equals(nullString, null));//此处没有NPE，true
        System.out.println(Objects.equals(null, null));//此处没有NPE，true
        System.out.println(Objects.equals(notNullString, "Hello World!"));//true
    }
}

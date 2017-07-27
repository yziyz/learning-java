package yz.learning.basic;

import java.util.Objects;

/**
 * @author 袁臻
 * 17-7-27
 *
 * 判空
 */
public class DetermineNull {
    public static void main(String[] args) {
        final String type = "city";
        System.out.println(Objects.equals(null, type));
        System.out.println(Objects.equals(null, ""));
    }
}

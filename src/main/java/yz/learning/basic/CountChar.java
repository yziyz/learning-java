package yz.learning.basic;

import java.util.Arrays;

public class CountChar {
    /**
     * 对一个字符数组中出现的字符进行计数
     * @param chars 需要计数的字符数组
     */
    private static void count(char[] chars) {
        //升序排列
        Arrays.sort(chars);

        //位置
        int position = 0;
        //计数
        int count = 0;
        //for循环对除字符数组中最后一类字符之外的字符进行计数，例如字符数组aabbcc，for循环对a和b进行计数
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] != chars[i + 1]) {
                //当前字符与下一个字符不同时，更新计数
                count = i + 1 - position;
                //更新位置
                position += count;
                //打印
                System.out.printf("%c: %d次\n", chars[i], count);
            }
        }
        //对字符数组的最后一类字符进行计数
        count = chars.length - position;
        System.out.printf("%c: %d次\n", chars[chars.length - 1], count);
    }

    public static void main(String[] args) {
        char[] chars = "dasdafasfjlkjlkjjaskdfjlaskdjflaskdjfalskdjf".toCharArray();

        count(chars);
    }
}

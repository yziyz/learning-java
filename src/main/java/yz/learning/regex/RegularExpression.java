package yz.learning.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegularExpression {
    public static void main(String[] args) {
        String string = "正则表达式语法";

        String patternString = "正则(.+?)语法";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(string);
        matcher.find();

        String value = matcher.group(1);
        System.out.println(value);
    }
}

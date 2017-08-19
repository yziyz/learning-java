package yz.learning.exception;

/**
 * @author 袁臻
 * 2017/08/19 15:18
 */
public class HandleMoreThanOneExceptions {
    public static void main(String[] args) {
        try {
            System.out.println("这是一个空操作");
        } catch (ArithmeticException | NullPointerException e) {
            //multi-catch parameter e may not be assigned
            //e = new ArithmeticException();
        }
    }
}

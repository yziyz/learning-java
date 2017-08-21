package yz.learning.exception;

import java.lang.reflect.Modifier;

/**
 * 在单个catch语句中捕获多种类型的异常时，类型对象参数被隐形设置为final
 *
 * @author 袁臻
 * 2017/08/19 15:18
 */
public class HandleMoreThanOneExceptions {
    public static void main(String[] args) {
        //单个catch语句中捕获多种类型的异常
        try {
            //抛出异常
            throw new ArithmeticException();
        } catch (ArithmeticException | NullPointerException e) {
            int modifier = e.getClass().getModifiers();
            //打印修饰符
            System.out.println(Modifier.toString(modifier));
        }

        //单个catch语句中捕获一种类型的异常
        try {
            //抛出异常
            throw new Exception();
        } catch (Exception e) {
            int modifier = e.getClass().getModifiers();
            //打印修饰符
            System.out.println(Modifier.toString(modifier));
        }
    }
}

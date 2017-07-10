package yz.learning.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 范型、继承和子类型
 */
public class GenericsAndInheritanceAndSubtypes {
    public static void main(String[] args) {
        /*
        如你所知，若类型是兼容的，则可以将一种类型的对象赋值给另一种类型的对象。
         */
        Object someObject = new Object();
        Integer someInteger = new Integer(12);
        someObject = someInteger;

        /*
        在面向对象的术语中，这被称为“是一种”关系。
        由于Integer是一种Object，所以赋值是允许的。
        但是Integer也是一个Number，所以下面的代码也是有效的：
         */
        someMethod(new Integer(12));
        someMethod(new Double(1.0));

        /*
        您可以执行通用类型调用，将Number作为其类型参数传递，并且如果参数与Number兼容，
        则将允许任何后续的add调用：
         */
        List<Number> numberList = new ArrayList<>(2);
        numberList.add(new Integer(1));
        numberList.add(new Double(1.0));

        /*
        listTest接受什么类型的参数？
        通过查看其签名，您可以看到它接受一个类型为Box<Number>的单个参数。
        但是，这是什么意思？ 您是否允许按照您的预期通过Box <Integer>或Box <Double>？
        答案是“否”，因为Box<Integer>和Box<Double>不是Box<Number>的子类型。
         */

        /*
        List<Integer> integerList = new ArrayList<>(1);
        listTest(integerList);
        Error:(41, 18) java: incompatible types: java.util.List<java.lang.Integer> cannot be converted to java.util.List<java.lang.Number>
         */

        /*
        给定两个具体类型A和B（例如Number和Integer），MyClass <A>与MyClass <B>无关，无论A和B是否相关。
        MyClass <A>和MyClass <B>的共同父代是Object。
         */
    }

    private static void someMethod(Number n) {}

    private static void listTest(List<Number> numberList) {}
}

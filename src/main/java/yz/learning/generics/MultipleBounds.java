package yz.learning.generics;

/**
 * 具有多个类型变量的边界
 */
public class MultipleBounds {
    class A{};
    interface B{};
    interface C{};
    //具有多个类型变量的边界中列出的所有类型的子类型。如果一个范围是一个类，则必须首先指定。
    class D <T extends A & B & C> {};

    //class E <T extends B & A & C> {};
    /*
    Error:(9, 28) java: interface expected here
     */
}

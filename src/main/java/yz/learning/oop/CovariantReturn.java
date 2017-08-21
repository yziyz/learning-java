package yz.learning.oop;

/**
 * 协变返回
 * https://blogs.oracle.com/sundararajan/covariant-return-types-in-java
 *
 * @author 袁臻
 * 2017/08/21 15:02
 */

class Shape {
}

class Circle extends Shape {
}

class ShapeFactory {
    public Shape newShape() {
        return new Shape();
    }
}

class CircleFactory extends ShapeFactory {
    @Override
    public Circle newShape() {
        return new Circle();
    }
}


public class CovariantReturn {
    /**
     * 测试方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        //创建一个CircleFactory实例cf
        CircleFactory cf = new CircleFactory();
        //使用CircleFactory#newShape方法创建Shape实例s
        Shape s = cf.newShape();
        //打印实例s的类名
        System.out.println(s.getClass().getCanonicalName());
    }
}
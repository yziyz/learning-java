package yz.learning.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试BoundedWildcards
 */
public class BoundedWildcards {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        canvas.draw(new Circle());
        canvas.draw(new Rectangle());

        List<Shape> shapes = new ArrayList<>(2);
        shapes.add(new Circle());
        shapes.add(new Rectangle());
        canvas.drawAll(shapes);
    }
}

/**
 * 抽象类Shape，形状
 */
abstract class Shape {
    public abstract void draw(Canvas c);
}

/**
 * Shape子类Circle，圆形
 */
class Circle extends Shape {
    private int x, y, radis;

    @Override
    public void draw(Canvas c) {
        System.out.println(this.getClass().getCanonicalName());
    }
}

/**
 * Shape子类Rectangle，矩形
 */
class Rectangle extends Shape {
    private int x, y, width, height;

    @Override
    public void draw(Canvas c) {
        System.out.println(this.getClass().getCanonicalName());
    }
}

class Canvas {
    public void draw(Shape s) {
        s.draw(this);
    }

    //可以在方法中读取
    public void drawAll(List<? extends Shape> shapes) { //bounded wildcards
        for (Shape s : shapes) {
            s.draw(this);
        }
    }

    //不能在方法中写入shapes
    public void addRectangle(List<? extends Shape> shapes) {
        //incompatible types: yz.learning.generics.Rectangle cannot be converted to capture#1 of ? extends yz.learning.generics.Shape
        //shapes.add(0, new Rectangle());
    }
}
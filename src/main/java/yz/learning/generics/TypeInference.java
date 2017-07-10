package yz.learning.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类型推断
 * 类型推断是Java编译器查看每个方法调用和相应声明的能力，以确定使调用适用的类型参数（或参数）。
 * 推理算法确定参数的类型，如果可用，则确定结果分配或返回的类型。
 * 最后，推理算法尝试找到与所有参数一起使用的最具体的类型。
 */
public class TypeInference {
    public static void main(String[] args) {
        Serializable s = pick("a", new ArrayList<String>());
        System.out.println(s.getClass().getCanonicalName());

        /*
        只要编译器可以从上下文推断类型参数，就可以使用空的类型参数（<>）来替换调用泛型构造函数所需的类型参数。
        这对尖括号是非正式地称为钻石。
         */
        ArrayList<Box<Integer>> boxArrayList = new ArrayList<>();
        BoxDemo.<Integer>addbox(10, boxArrayList);
        BoxDemo.addbox(20, boxArrayList);
        BoxDemo.addbox(30, boxArrayList);
        BoxDemo.outputBoxes(boxArrayList);
    }

    private static <T> T pick(T a1, T a2) {
        return a2;
    }
}

class BoxDemo {
    public static <U> void addbox(U u, List<Box<U>> boxes) {
        Box<U> box = new Box<>();
        box.setT(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> box : boxes) {
            U boxContents = box.getT();
            System.out.println("Box #" + counter + " contains [" + boxContents.toString() + "]");
            counter++;
        }
    }
}
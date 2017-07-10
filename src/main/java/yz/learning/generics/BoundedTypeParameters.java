package yz.learning.generics;

public class BoundedTypeParameters {
    public static void main(String[] args) {
        Box<Integer> integerBag = new Box<>();
        integerBag.setT(10);
        integerBag.inspect(1.0);

        //integerBag.inspect("some");
        /*
         java: method inspect in class yz.learning.generics.Box<T> cannot be applied to given types;
         required: U
         found: java.lang.String
         reason: inferred type does not conform to upper bound(s)
         inferred: java.lang.String
         upper bound(s): java.lang.Number
         */
    }
}

class Box<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public <U extends Number> void inspect(U u) {
        /*
        可能有时候要限制在参数化类型中可以用作类型参数的类型。
        例如，对数字进行操作的方法可能只希望接受Number或其子类的实例。
        这是有界类型参数。
        要声明一个有界类型的参数，列出type参数的名称，后跟extend关键字，后跟其上限，在此示例中为Number。
        请注意，在这种情况下，扩展在一般意义上用于表示“扩展”（如在类中）或“实现”（如在接口中）。
         */
        System.out.println("T: " + t.getClass().getCanonicalName());
        System.out.println("U: " + u.getClass().getCanonicalName());
    }
}

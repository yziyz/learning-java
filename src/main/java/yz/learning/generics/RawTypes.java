package yz.learning.generics;

public class RawTypes {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        Box rawBox01 = stringBox;
        rawBox01.display(8);

        Box rawBox02 = new Box();//rawBox02 is a raw type of Box<T>
        Box<Integer> integerBox = rawBox02;
    }

    private static class Box<T> {
        void display(T t) {
            System.out.println(
                    t.getClass().getCanonicalName()
            );
        }
    }
}

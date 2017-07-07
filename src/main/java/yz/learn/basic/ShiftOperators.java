package yz.learn.basic;

/**
 * 位操作符
 * https://stackoverflow.com/questions/10910913/how-do-shift-operators-work-in-java
 */
public class ShiftOperators {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(8));
        //1000

        //Signed left shift operator
        System.out.println(Integer.toBinaryString(8 >> 2));
        //10

        //Signed right shift operator
        System.out.println(Integer.toBinaryString(8 << 2));
        //100000

        //Unsigned right shift operator
        System.out.println(Integer.toBinaryString(8 >>> 2));
        //10

        System.out.println(Integer.toBinaryString(-8));
        //11111111111111111111111111111000
        System.out.println(Integer.toBinaryString(-8 >> 2));
        //11111111111111111111111111111110
        System.out.println(Integer.toBinaryString(-8 << 2));
        //11111111111111111111111111100000
        System.out.println(Integer.toBinaryString(-8 >>> 2));
        //111111111111111111111111111110
    }
}

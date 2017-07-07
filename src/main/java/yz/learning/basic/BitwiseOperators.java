package yz.learning.basic;

/**
 * 位操作符
 * https://zh.wikipedia.org/zh-hans/%E4%BD%8D%E6%93%8D%E4%BD%9C
 * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
 */
public class BitwiseOperators {
    public static void main(String[] args) {
        int bitMask = 0x000F;
        int val = 0x2222;
        System.out.println(Integer.toBinaryString(bitMask) + " : " + bitMask);
        //1111 : 15
        System.out.println(Integer.toBinaryString(val) + " : " + val);
        //10001000100010 : 8738

        //按位与(AND)
        System.out.println(Integer.toBinaryString(val & bitMask) + " : " + (val & bitMask));
        //10 : 2

        //按位异或(XOR, bitwise exclusive OR)
        System.out.println(Integer.toBinaryString(val ^ bitMask) + " : " + (val ^ bitMask));
        //10001000101101 : 8749

        //按位或(OR, bitwise inclusive OR)
        System.out.println(Integer.toBinaryString(val | bitMask) + " : " + (val | bitMask));
        //10001000101111 : 8751

        //取反(NOT)
        System.out.println(Integer.toBinaryString(~bitMask) + " : " + ~bitMask);
        //11111111111111111111111111110000 : -16
    }
}

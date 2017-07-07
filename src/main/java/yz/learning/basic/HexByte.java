package yz.learning.basic;

/**
 * 十六进制位
 */
public class HexByte {
    public static void main(String[] args) {
        char hex[] = {
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };

        byte b = (byte) 0xf1;

        System.out.printf("b = 0x%c%c\n", hex[(b >> 4) & 0xf], hex[b & 0xf]);
        //b = 0xf1
        System.out.println((b >> 4) & 0xf);
        System.out.println(Integer.toBinaryString(b >> 4));
        System.out.println(Integer.toBinaryString(0xf));
        System.out.println(Integer.toBinaryString((b >> 4) & 0xf));
        //15
        System.out.println(b & 0xf);
        //1
    }
}

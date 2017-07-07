package yz.learn.oop;

/**
 * 包装类
 */
public class Wrapper {
    public static void main(String[] args) {
        /*
        下面三行企图比较两个Integer的大小
         */
        Integer integer1 = new Integer(1);
        Integer integer2 = new Integer(1);
        System.out.println(integer1 == integer2);//false
        System.out.println(integer1.equals(integer2));//true
    }
}

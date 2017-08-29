package yz.learning.oop;

/**
 * Test of Shadow
 *
 * @author 袁臻
 * 2017/08/29 20:19
 */
public class ShadowTest {

    private int x = 0;

    //an inner class
    class FirstLevel {

        private int x = 1;

        void methodInFirstLevel(int x) {
            System.out.println("x: " + x);
            System.out.println("this.x: " + this.x);
            System.out.println("ShadowTest.this.x: " + ShadowTest.this.x);
        }
    }

    public static void main(String[] args) {
        ShadowTest shadowTest = new ShadowTest();
        ShadowTest.FirstLevel firstLevel = shadowTest.new FirstLevel();
        firstLevel.methodInFirstLevel(23);
    }
}

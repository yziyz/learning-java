package yz.learn.concurrency;

/**
 * Synchronized测试
 */
public class SynchronizedTest {
    synchronized void test(String name) {
        for (int i = 0; i < 10; i++) {
            Printer.print(name + " :: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        new TestThread("THREAD 1", test);
        new TestThread("THREAD 2", test);
        new TestThread("THREAD 3", test);
    }
}

class TestThread implements Runnable{

    String name;
    SynchronizedTest test;

    TestThread(String name, SynchronizedTest test) {
        this.name = name;
        this.test = test;
        new Thread(this).start();
    }

    @Override
    public void run() {
        test.test(name);
    }
}

class Printer {
    static void print(String s) {
        System.out.println(s);
    }
}
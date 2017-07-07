package yz.learn.concurrency;

/**
 * Runnable例子。
 */
public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello form a thread!");
    }

    public static void main(String[] args) {
        (new Thread(new HelloRunnable())).start();
    }
}

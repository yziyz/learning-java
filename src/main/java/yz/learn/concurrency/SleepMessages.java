package yz.learn.concurrency;

/**
 * Thread.sleep()例子。
 */
public class SleepMessages {
    public static void main(String[] args) throws InterruptedException{
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        for (String message : importantInfo) {
            Thread.sleep(2000);
            System.out.println(message);
        }
    }
}

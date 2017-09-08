package yz.learning.concurrency;

/**
 * Simple Threads
 */
public class SimpleThreads {
    /**
     * 打印一条信息，以线程名称开头
     *
     * @param message 需要打印的信息
     */
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {

        @Override
        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };

            try {
                for (String anImportantInfo : importantInfo) {
                    Thread.sleep(4000);
                    threadMessage(anImportantInfo);
                }
            } catch (InterruptedException e) {
                System.err.print(Thread.currentThread().getName() + ": ");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        long patience = 1000L * 5L;

        if (args.length > 0) {
            patience = Long.parseLong(args[0]) * 1000;
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.setName("MessageLoop");
        t.start();

        threadMessage("Waiting for MessageLoop to finish");
        //循环直至MessageLoop线程结束
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                t.join();
            }
        }

        threadMessage("Finished");
    }
}

/*
main: Starting MessageLoop thread
main: Waiting for MessageLoop to finish
main: Still waiting...
main: Still waiting...
main: Still waiting...
main: Still waiting...
MessageLoop: Mares eat oats
main: Still waiting...
MessageLoop: java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at yz.learning.concurrency.SimpleThreads$MessageLoop.run(SimpleThreads.java:30)
	at java.lang.Thread.run(Thread.java:748)
main: Tired of waiting!
main: Finished
 */
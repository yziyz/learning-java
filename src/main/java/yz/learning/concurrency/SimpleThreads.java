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
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        long patience = 1000 * 60 * 60;

        if (args.length > 0) {
            patience = Long.parseLong(args[0]) * 1000;
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                t.join();
            }
        }
    }
}

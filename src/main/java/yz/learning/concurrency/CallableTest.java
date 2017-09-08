package yz.learning.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Callable 测试
 *
 * @author 袁臻
 * 2017/09/08 16:56
 */
public class CallableTest {
    static class WordLengthCallable implements Callable<Integer> {
        private String word;

        public WordLengthCallable(String word) {
            this.word = word;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println(System.currentTimeMillis());
            return word.length();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Set<Future<Integer>> set = new HashSet<>();

        String[] words = new String[]{"北京", "欢迎", "你们"};

        for (String word : words) {
            WordLengthCallable callable = new WordLengthCallable(word);
            Future<Integer> future = pool.submit(callable);
            set.add(future);
        }

        int sum = 0;

        for (Future<Integer> future : set) {
            sum += future.get();
        }

        System.out.println(sum);
    }
}

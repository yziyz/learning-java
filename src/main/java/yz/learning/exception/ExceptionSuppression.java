package yz.learning.exception;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 异常抑制
 * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html#suppressed-exceptions
 *
 * @author 袁臻
 * 2017/08/19 16:39
 */
public class ExceptionSuppression {
    /**
     * 测试方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        try (MyResource myResource = new MyResource()) {
            Thread.sleep(1000L);
            //抛出抑制MyResource构造方法中的异常
            throw new MyException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 一个自定义的资源
 */
class MyResource implements Closeable, Runnable {

    /**
     * 构造方法，抛出被抑制的异常
     */
    MyResource() {
        //启动抛出异常的线程
        this.run();
    }

    @Override
    public void close() throws IOException {
        System.out.println(LocalDateTime.now() + " Closed");
    }

    @Override
    public void run() {
        //休眠2秒
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalDateTime.now() + " Throws an exception");
        //抛出异常
        throw new UnsupportedOperationException();
    }
}

/**
 * 一个自定义的异常
 */
class MyException extends Exception {
    /**
     * 构造方法
     */
    MyException() {
        super(LocalDateTime.now().toString());
    }
}
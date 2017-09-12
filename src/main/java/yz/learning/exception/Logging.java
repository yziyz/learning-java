package yz.learning.exception;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志
 *
 * @author 袁臻
 * 2017/08/31 13:40
 */
public class Logging {
    public static void main(String[] args) {
        try {
            Handler handler = new FileHandler("outFile.log");
            Logger.getLogger("").addHandler(handler);
            //抛出异常
            throw new IOException();
        } catch (IOException e) {
            Logger logger = Logger.getLogger("yz.learning.exception");
            StackTraceElement[] elements = e.getStackTrace();
            for (StackTraceElement element : elements) {
                String log = element.getFileName() + ": " + element.getLineNumber() + " >> " + element.getMethodName() + "() ";
                logger.log(Level.WARNING, log);
            }
        }
    }
}

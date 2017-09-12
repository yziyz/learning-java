package yz.learning.exception;

/**
 * 堆栈跟踪
 *
 * @author 袁臻
 * 2017/08/31 13:35
 */
public class StackTrace {
    public static void main(String[] args) {
        try {
            throw new IllegalAccessException();
        } catch (Exception e) {
            StackTraceElement[] elements = e.getStackTrace();
            for (StackTraceElement element : elements) {
                System.err.println(element.getFileName() + ": " +
                        element.getLineNumber() + " >> " + element.getMethodName() + "()");
            }
        }
    }
}

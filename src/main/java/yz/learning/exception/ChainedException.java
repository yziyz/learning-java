package yz.learning.exception;

/**
 * 链式异常
 *
 * @author 袁臻
 * 2017/08/31 13:31
 */
public class ChainedException {
    public static void main(String[] args) throws SimpleException {
        try {
            //Do something
        } catch (NullPointerException e) {
            throw new SimpleException("SimpleException", e);
        }
    }
}

class SimpleException extends Throwable {
    SimpleException(String message, Throwable cause) {
        super(message, cause);
    }
}

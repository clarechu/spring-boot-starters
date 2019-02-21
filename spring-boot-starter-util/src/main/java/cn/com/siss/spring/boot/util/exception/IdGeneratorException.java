package cn.com.siss.spring.boot.util.exception;

/**
 * @ClassName IdGeneratorException
 * @Description TODO
 * @Author clare
 * @Date 2019/2/21 15:43
 * @Version 1.0
 */
public class IdGeneratorException extends Exception{
    private static final long serialVersionUID = 1L;

    public IdGeneratorException(String message) {
        super(message);
    }

    public IdGeneratorException(Throwable throwable) {
        super(throwable);
    }

    public IdGeneratorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

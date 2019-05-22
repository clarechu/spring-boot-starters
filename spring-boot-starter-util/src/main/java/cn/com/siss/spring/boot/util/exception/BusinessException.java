package cn.com.siss.spring.boot.util.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }

}

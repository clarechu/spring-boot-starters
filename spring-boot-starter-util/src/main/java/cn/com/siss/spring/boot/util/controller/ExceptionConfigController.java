package cn.com.siss.spring.boot.util.controller;

import cn.com.siss.spring.boot.util.base.BackResponseUtil;
import cn.com.siss.spring.boot.util.base.BaseResponse;
import cn.com.siss.spring.boot.util.base.ReturnCodeEnum;
import cn.com.siss.spring.boot.validate.exception.DataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionConfigController
 * @Description 父类
 * @Author clare
 * @Date 2019/3/22 13:40
 * @Version 1.0
 */

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionConfigController {

    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointerExceptionHandler(NullPointerException e){
        log.error(e.getMessage(),e);
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_NULL_POINTER_EXCEPTION.getCode());
        baseResponse.setMessage(message);
        return baseResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_RUNTIME_EXCEPTION.getCode());
        baseResponse.setMessage(message);
        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exception(Exception e){
        log.error(e.getMessage(),e);
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_EXCEPTION.getCode());
        baseResponse.setMessage(message);
        return baseResponse;
    }

    @ExceptionHandler(DataException.class)
    public BaseResponse dataException(Exception e){
        log.error(e.getMessage(),e);
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_PAYMENT_TYPE_ERROR.getCode());
        baseResponse.setMessage(message);
        return baseResponse;
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse bindException(BindException e){
        log.error(e.getMessage(),e);
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_EXCEPTION.getCode());
        baseResponse.setMessage(e.getAllErrors().get(0).getDefaultMessage());
        return baseResponse;
    }

}

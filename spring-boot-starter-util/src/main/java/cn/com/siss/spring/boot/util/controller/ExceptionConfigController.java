package cn.com.siss.spring.boot.util.controller;

import cn.com.siss.spring.boot.util.base.BackResponseUtil;
import cn.com.siss.spring.boot.util.base.BaseResponse;
import cn.com.siss.spring.boot.util.base.ReturnCodeEnum;
import cn.com.siss.spring.boot.validate.exception.DataException;
import org.springframework.beans.factory.annotation.Configurable;
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
public class ExceptionConfigController {

    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointerExceptionHandler(NullPointerException e){
        e.printStackTrace();
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_NULL_POINTER_EXCEPTION.getCode());
        baseResponse.setData(message);
        return baseResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e){
        e.printStackTrace();
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_RUNTIME_EXCEPTION.getCode());
        baseResponse.setData(message);
        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exception(Exception e){
        e.printStackTrace();
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_EXCEPTION.getCode());
        baseResponse.setData(message);
        return baseResponse;
    }

    @ExceptionHandler(DataException.class)
    public BaseResponse dataException(Exception e){
        e.printStackTrace();
        String message = e.getMessage();
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_PAYMENT_TYPE_ERROR.getCode());
        baseResponse.setData(message);
        return baseResponse;
    }
}

package cn.com.siss.spring.boot.util.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionConfigController
 * @Description TODO
 * @Author clare
 * @Date 2019/3/22 13:40
 * @Version 1.0
 */

@ControllerAdvice
@ResponseBody
public class ExceptionConfigController {

    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointerExceptionHandler(NullPointerException e){
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_NULL_POINTER_EXCEPTION.getCode());
        return baseResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e){
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_RUNTIME_EXCEPTION.getCode());
        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exception(Exception e){
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_RUNTIME_EXCEPTION.getCode());
        return baseResponse;
    }
}

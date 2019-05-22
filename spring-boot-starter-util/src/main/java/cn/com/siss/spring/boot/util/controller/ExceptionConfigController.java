package cn.com.siss.spring.boot.util.controller;

import cn.com.siss.spring.boot.util.base.BackResponseUtil;
import cn.com.siss.spring.boot.util.base.BaseResponse;
import cn.com.siss.spring.boot.util.base.ReturnCodeEnum;
import cn.com.siss.spring.boot.util.exception.BusinessException;
import cn.com.siss.spring.boot.util.other.StringUtil;
import cn.com.siss.spring.boot.validate.exception.DataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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
public class ExceptionConfigController implements ResponseBodyAdvice {

    private static Map<String,String> messageMap=new HashMap<>();

    @PostConstruct
    private void init(){
        messageMap.put("message.common.success","成功");
        messageMap.put("message.common.failed","失败");
        messageMap.put("message.common.information.nonexistence","信息不存在");
        messageMap.put("message.payment.type.error","支付方式错误");
        messageMap.put("message.common.server.error","服务器处理异常");
        messageMap.put("message.common.parameters.missing","参数不全");
        messageMap.put("message.common.timeout","请求超时");
        messageMap.put("message.common.information.already.exists","信息已存在");
        messageMap.put("message.common.session.expired","登录状态失效");
        messageMap.put("message.security.auth.failed","验证失败");
        messageMap.put("message.security.unauthorized","无权限访问");
        messageMap.put("message.common.insert.failed","插入失败");
        messageMap.put("message.common.delete.failed","删除失败");
    }

    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointerExceptionHandler(NullPointerException e){
        log.error(e.getMessage(),e);
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_NULL_POINTER_EXCEPTION.getCode());
        baseResponse.setMessage(getMessage(ReturnCodeEnum.MESSAGE_COMMON_SERVER_ERROR.getMessage()));
        return baseResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_RUNTIME_EXCEPTION.getCode());
        baseResponse.setMessage(getMessage(ReturnCodeEnum.MESSAGE_COMMON_SERVER_ERROR.getMessage()));
        return baseResponse;
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException e){
        log.error(e.getMessage(),e);
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_RUNTIME_EXCEPTION.getCode());
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exception(Exception e){
        log.error(e.getMessage(),e);
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_EXCEPTION.getCode());
        baseResponse.setMessage(getMessage(ReturnCodeEnum.MESSAGE_COMMON_SERVER_ERROR.getMessage()));
        return baseResponse;
    }

    @ExceptionHandler(DataException.class)
    public BaseResponse dataException(Exception e){
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_PAYMENT_TYPE_ERROR.getCode());
        baseResponse.setMessage(e.getMessage());
        return baseResponse;
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse bindException(BindException e){
        log.error(e.getMessage(),e);
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_EXCEPTION.getCode());
        baseResponse.setMessage(e.getAllErrors().get(0).getDefaultMessage());
        return baseResponse;
    }

    private String getMessage(String msg){
        String message=messageMap.get(msg);
        if (StringUtil.isNotEmpty(message)){
            return message;
        }
        return msg;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof BaseResponse){
            BaseResponse baseResponse=(BaseResponse)o;
            baseResponse.setMessage(getMessage(baseResponse.getMessage()));
            return baseResponse;
        }
        return o;
    }
}

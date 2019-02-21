package cn.com.siss.spring.boot.util.base;

/**
 * @ClassName BackResponseUtil
 * @Description 获取 baseResponse 基类
 * @Author clare
 * @Date 2019/2/19 13:41
 * @Version 1.0
 */
public class BackResponseUtil {

    //获取 BaseResponse
    public static BaseResponse setBaseResponse(Integer code) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        String message = ReturnCodeEnum.getName(code);
        baseResponse.setMessage(message);
        return baseResponse;
    }
}

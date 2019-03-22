package cn.com.siss.spring.boot.util.base;

/**
 * @ClassName ReturnCodeEnum
 * @Description 接口返回值枚举
 * @Author clare
 * @Date 2019/2/19 11:25
 * @Version 1.0
 */
public enum ReturnCodeEnum {
    MESSAGE_COMMON_SUCCESS(1000, MessageCodeConstant.MESSAGE_COMMON_SUCCESS),// 数据处理成功
    MESSAGE_SECURITY_UNAUTHORIZED(1001, MessageCodeConstant.MESSAGE_SECURITY_UNAUTHORIZED),// 系统应用间对签名或TOKEN验证不通过
    MESSAGE_COMMON_INFORMATION_NONEXISTENCE(1002, MessageCodeConstant.MESSAGE_COMMON_INFORMATION_NONEXISTENCE),// 数据信息不存在
    MESSAGE_PAYMENT_TYPE_ERROR(1003, MessageCodeConstant.MESSAGE_PAYMENT_TYPE_ERROR),// 数据错误，数据校验不通过，数据不符合接口规则
    MESSAGE_COMMON_SERVER_ERROR(1004, MessageCodeConstant.MESSAGE_COMMON_SERVER_ERROR),// 异常
    MESSAGE_COMMON_FAILED(1005, MessageCodeConstant.MESSAGE_COMMON_FAILED),// 数据处理失败，如：保存、发送
    MESSAGE_COMMON_PARAMETERS_MISSING(1006, MessageCodeConstant.MESSAGE_COMMON_PARAMETERS_MISSING),// 用户输入或接口入参缺少
    MESSAGE_COMMON_TIMEOUT(1007, MessageCodeConstant.MESSAGE_COMMON_TIMEOUT),// 系统应用间通讯超时
    MESSAGE_COMMON_INFORMATION_ALREADY_EXISTS(1008, MessageCodeConstant.MESSAGE_COMMON_INFORMATION_ALREADYEXISTS),//数据重复
    MESSAGE_COMMON_SESSION_EXPIRED(1009, MessageCodeConstant.MESSAGE_COMMON_SESSION_EXPIRED),// 客户端TOKEN验证不通过或用户登录账户相关问题
    MESSAGE_SECURITY_AUTH_FAILED(1010, MessageCodeConstant.MESSAGE_SECURITY_AUTH_FAILED),//短信或者邮箱验证失败
    MESSAGE_COMMON_INSERT_FAILED(1011, MessageCodeConstant.MESSAGE_COMMON_INSERT_FAILED),//插入失败
    MESSAGE_COMMON_DELETE_FAILED(1012, MessageCodeConstant.MESSAGE_COMMON_DELETE_FAILED),//删除失败
    MESSAGE_NULL_POINTER_EXCEPTION(1013, MessageCodeConstant.MESSAGE_NULL_POINTER_EXCEPTION),
    MESSAGE_RUNTIME_EXCEPTION(1012, MessageCodeConstant.MESSAGE_RUNTIME_EXCEPTION);

    /**
     * 业务编号
     */
    private Integer code;

    /**
     * 业务值
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ReturnCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getName(Integer code) {
        for (ReturnCodeEnum returnCode : ReturnCodeEnum.values()) {
            if (returnCode.code.equals(code)) {
                return returnCode.message;
            }
        }
        return null;
    }
}

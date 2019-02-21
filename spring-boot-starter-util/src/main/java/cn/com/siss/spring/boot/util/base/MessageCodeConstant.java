package cn.com.siss.spring.boot.util.base;

import java.io.Serializable;

/**
 * @ClassName MessageCodeConstant
 * @Description 国际化message基础常量
 * @Author clare
 * @Date 2019/2/19 11:25
 * @Version 1.0
 */
public class MessageCodeConstant implements Serializable {
    /**
     * 成功
     */
    public static final String MESSAGE_COMMON_SUCCESS = "message.common.success";

    /**
     * 失败
     */
    public static final String MESSAGE_COMMON_FAILED = "message.common.failed";

    /**
     * 信息不存在
     */
    public static final String MESSAGE_COMMON_INFORMATION_NONEXISTENCE = "message.common.information.nonexistence";

    /**
     * 支付方式错误
     */
    public static final String MESSAGE_PAYMENT_TYPE_ERROR = "message.payment.type.error";

    /**
     * 服务器处理异常
     */
    public static final String MESSAGE_COMMON_SERVER_ERROR = "message.common.server.error";

    /**
     * 参数不全
     */
    public static final String MESSAGE_COMMON_PARAMETERS_MISSING = "message.common.parameters.missing";

    /**
     * 请求超时
     */
    public static final String MESSAGE_COMMON_TIMEOUT = "message.common.timeout";

    /**
     * 信息已存在
     */
    public static final String MESSAGE_COMMON_INFORMATION_ALREADYEXISTS = "message.common.information.already.exists";

    /**
     * 登录状态失效
     */
    public static final String MESSAGE_COMMON_SESSION_EXPIRED = "message.common.session.expired";

    /**
     * 验证失败
     */
    public static final String MESSAGE_SECURITY_AUTHFAILED = "message.security.authfailed";

    /**
     * 无权限访问
     */
    public static final String MESSAGE_SECURITY_UNAUTHORIZED = "message.security.unauthorized";

    /**
     * 插入失败
     */
    public static final String MESSAGE_COMMON_INSERTFAILD = "message.common.insert.faild";

    /**
     * 删除失败
     */
    public static final String MESSAGE_COMMON_DELETEFAILD = "message.common.delete.faild";

}

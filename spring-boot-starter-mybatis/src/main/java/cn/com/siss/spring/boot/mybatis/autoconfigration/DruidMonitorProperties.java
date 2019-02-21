package cn.com.siss.spring.boot.mybatis.autoconfigration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName DruidMonitorProperties
 * @Description TODO
 * @Author clare
 * @Date 2019/2/20 18:24
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "spring.datasource.druid.stat-view-servlet")
@Component
@Data
public class DruidMonitorProperties {
    /**
     * 白名单
     */
    private String allow;
    /**
     * IP黑名单(存在共同时，deny优先于allow)
     */
    private String deny;
    /**
     * 登录查看信息的账号
     */
    private String loginUsername;

    /**
     * 登录查看信息的密码
     */
    private String loginPassword;


}

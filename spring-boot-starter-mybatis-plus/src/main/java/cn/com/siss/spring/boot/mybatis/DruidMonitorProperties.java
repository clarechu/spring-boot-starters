package cn.com.siss.spring.boot.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by  siss on 16-10-9.
 * PackageName
 * ModifyDate  16-10-9
 * Description (constants prop 配置类)
 * ProjectName cmos-finance
 */
@ConfigurationProperties(prefix = "druid")
public class DruidMonitorProperties {

    /**
     * 白名单
     */
    private String allow ;

    /**
     * IP黑名单(存在共同时，deny优先于allow)
     */
    private String deny ;

    /**
     * 登录查看信息的账号
     */
    private String loginUsername = "admin";

    /**
     * 登录查看信息的密码
     */
    private String loginPassword = "admin123";

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public String getDeny() {
        return deny;
    }

    public void setDeny(String deny) {
        this.deny = deny;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}


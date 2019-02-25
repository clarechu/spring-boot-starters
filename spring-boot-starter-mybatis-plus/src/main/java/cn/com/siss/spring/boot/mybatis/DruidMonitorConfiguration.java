package cn.com.siss.spring.boot.mybatis;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by
 * PackageName
 * ModifyDate  16-10-14
 * Description
 * ProjectName spring-boot-starters
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(DruidMonitorProperties.class)
public class DruidMonitorConfiguration {

    /**
     * 注册一个StatViewServlet
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet(DruidMonitorProperties druidMonitorProperties){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数：initParams
        //白名单：
        if (!StringUtils.isEmpty(druidMonitorProperties.getAllow())){
            servletRegistrationBean.addInitParameter("allow", druidMonitorProperties.getAllow());
        }


        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        if (!StringUtils.isEmpty(druidMonitorProperties.getDeny())){
            servletRegistrationBean.addInitParameter("deny", druidMonitorProperties.getDeny());
        }

        //登录查看信息的账号密码.

        servletRegistrationBean.addInitParameter("loginUsername", druidMonitorProperties.getLoginUsername());

        servletRegistrationBean.addInitParameter("loginPassword", druidMonitorProperties.getLoginPassword());

        //是否能够重置数据.

        servletRegistrationBean.addInitParameter("resetEnable","false");

        return servletRegistrationBean;

    }
    /**
     * 注册一个：filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");

        return filterRegistrationBean;
    }

}

package cn.com.siss.spring.boot.core.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by  siss on 16-10-17.
 * PackageName
 * ModifyDate  16-10-17
 * Description (CORS全局配置)
 * ProjectName
 */
@Configuration
@ConditionalOnProperty(prefix = "cors", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({CorsProperties.class})
public class CorsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CorsConfiguration corsConfiguration(){
        return new CorsConfiguration();
    }

}

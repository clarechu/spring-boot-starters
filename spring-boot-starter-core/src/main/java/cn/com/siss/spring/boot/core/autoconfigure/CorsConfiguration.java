package cn.com.siss.spring.boot.core.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by  vpclub on 16-10-17.
 * PackageName
 * ModifyDate  16-10-17
 * Description (CORS全局配置)
 * ProjectName
 */
@Configuration
@EnableConfigurationProperties({CorsProperties.class})
public class CorsConfiguration extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(CorsConfiguration.class);
    @Autowired
    private CorsProperties corsProperties;

    private String[] DEFAULT_ORIGINS = {"*"};

    private String[] DEFAULT_ALLOWED_HEADERS = {"*"};
    private String[] DEFAULT_METHODS = {};

    private boolean DEFAULT_ALLOW_CREDENTIALS = true;
    private long DEFAULT_MAX_AGE = 1800;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedOrigins = corsProperties.getAllowedOrigins();

        String[] allowedHeaders = corsProperties.getAllowedHeaders();

        String[] exposedHeaders = corsProperties.getExposedHeaders();

        Boolean allowCredentials = corsProperties.getAllowCredentials();

        Long maxAge = corsProperties.getMaxAge();
        logger.info("registry = [" + allowedOrigins + "]");
        String mappings = corsProperties.getMappings();
        if (allowedHeaders == null || allowedHeaders.length == 0) {
            allowedHeaders = DEFAULT_ALLOWED_HEADERS;
        }
        if (allowedOrigins == null || allowedOrigins.length == 0) {
            allowedOrigins = DEFAULT_ORIGINS;
        }

        if (exposedHeaders == null || exposedHeaders.length == 0) {
            exposedHeaders = DEFAULT_METHODS;
        }
        if (maxAge == null || maxAge == 0) {
            maxAge = DEFAULT_MAX_AGE;
        }
        if (allowCredentials == null) {
            allowCredentials = DEFAULT_ALLOW_CREDENTIALS;
        }
        if (mappings == null || mappings.trim() == "") {
            mappings = "/**";
        }
        logger.info("mappings is " + mappings);
        registry.addMapping(mappings)
                .allowedOrigins(allowedOrigins)
                .allowedHeaders(allowedHeaders)
                .exposedHeaders(exposedHeaders)
                .allowCredentials(allowCredentials).maxAge(maxAge);
    }
}

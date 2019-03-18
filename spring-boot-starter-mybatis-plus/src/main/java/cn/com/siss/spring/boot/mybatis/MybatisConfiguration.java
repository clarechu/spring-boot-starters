package cn.com.siss.spring.boot.mybatis;

import cn.com.siss.spring.boot.mybatis.rws.DataSourceConfiguration;
import cn.com.siss.spring.boot.mybatis.rws.DynamicDataSourceTransactionManager;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;


/**
 * MyBatis Java 配置 MybatisConfiguration
 *
 * @author
 */

@Slf4j
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@EnableConfigurationProperties({MybatisProperties.class})
@ConditionalOnProperty(prefix = "mybatisplus", name = "enabled", havingValue = "true")
public class MybatisConfiguration {

    @Autowired
    @Qualifier("customDataSource")
    private DataSource dataSource;

    @Autowired
    private MybatisProperties properties;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    @Qualifier("customInterceptors")
    private Interceptor[] customInterceptors;

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory mybatisSqlSessionFactory() {
        try {
            MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            sessionFactoryBean.setVfs(SpringBootVFS.class);

            if (StringUtils.hasText(this.properties.getConfigLocation())) {
                sessionFactoryBean.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
            } else {
                com.baomidou.mybatisplus.MybatisConfiguration mc = new com.baomidou.mybatisplus.MybatisConfiguration();
                // 对于完全自定义的mapper需要加此项配置，才能实现下划线转驼峰

                //mc.setMapUnderscoreToCamelCase(true);

                mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
                sessionFactoryBean.setConfiguration(mc);
            }

            if (!ObjectUtils.isEmpty(this.customInterceptors)) {
                sessionFactoryBean.setPlugins(this.customInterceptors);
            }
            // MP 全局配置，更多内容进入类看注释

            GlobalConfiguration globalConfig = new GlobalConfiguration();
            globalConfig.setDbType(DBType.MYSQL.name());//数据库类型

            // ID 策略 AUTO->`0`("数据库ID自增") INPUT->`1`(用户输入ID") ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")

            globalConfig.setIdType(2);
            //MP 属性下划线 转 驼峰 , 如果原生配置 mc.setMapUnderscoreToCamelCase(true) 开启，该配置可以无。

            sessionFactoryBean.setGlobalConfig(globalConfig);

            if (this.databaseIdProvider != null) {
                sessionFactoryBean.setDatabaseIdProvider(this.databaseIdProvider);
            }
            if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
                sessionFactoryBean.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
            }
            if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
                sessionFactoryBean.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
            }
            if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
                sessionFactoryBean.setMapperLocations(this.properties.resolveMapperLocations());
            }


            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error("{}", e);
            return null;
        }
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DynamicDataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        return dataSourceTransactionManager;
    }

}

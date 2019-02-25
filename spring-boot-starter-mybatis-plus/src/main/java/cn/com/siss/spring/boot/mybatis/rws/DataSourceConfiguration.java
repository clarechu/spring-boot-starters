package cn.com.siss.spring.boot.mybatis.rws;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "mybatisplus.rws", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({DataSourceProperties.class})
public class DataSourceConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

	@Value("${spring.datasource.type}")
	private Class<? extends DruidDataSource> dataSourceType;

	/**
	 * 写库 数据源配置
	 */
	@Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DruidDataSource writeDataSource() {
        log.debug("=== writeDataSource ===");
        return (DruidDataSource)DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	/**
     * 从库配置
     */
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DruidDataSource readDataSource() {
        log.debug("=== readDataSource ===");
        return (DruidDataSource)DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "customDataSource")
    public DynamicDataSource dataSource() {
        return new DynamicDataSource(readDataSource(), writeDataSource());
    }

    @Bean(name = "customInterceptors")
    public Interceptor[] customInterceptors() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return new Interceptor[] {
                paginationInterceptor,
                new DynamicDataSourceInterceptor()
        };
    }
}

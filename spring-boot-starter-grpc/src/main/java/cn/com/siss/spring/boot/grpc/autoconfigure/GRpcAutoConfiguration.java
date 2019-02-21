package cn.com.siss.spring.boot.grpc.autoconfigure;

import cn.com.siss.spring.boot.grpc.annotations.GRpcClient;
import cn.com.siss.spring.boot.grpc.annotations.GRpcService;
import cn.com.siss.spring.boot.grpc.properties.GRpcChannelsProperties;
import cn.com.siss.spring.boot.grpc.properties.GRpcServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GRpcAutoConfiguration
 *
 * @author John Deng
 */
@Configuration
@ConditionalOnClass({GRpcChannelFactory.class})
@EnableConfigurationProperties({GRpcServerProperties.class, GRpcChannelsProperties.class})
public class GRpcAutoConfiguration {

    private final GRpcServerProperties gRpcServerProperties;

    private final GRpcChannelsProperties gRpcChannelsProperties;

    public GRpcAutoConfiguration(GRpcServerProperties gRpcServerProperties, GRpcChannelsProperties gRpcChannelsProperties) {
        this.gRpcServerProperties = gRpcServerProperties;
        this.gRpcChannelsProperties = gRpcChannelsProperties;
    }

    @Bean
    @ConditionalOnBean(annotation = GRpcService.class)
    public GRpcServerRunner grpcServerRunner() {
        return new GRpcServerRunner();
    }

    @Bean
    public GlobalClientInterceptorRegistry globalClientInterceptorRegistry() {
        return new GlobalClientInterceptorRegistry();
    }

    @Bean
    @ConditionalOnMissingBean(value = GRpcChannelFactory.class)
    public GRpcChannelFactory addressChannelFactory(GRpcChannelsProperties gRpcChannelsProperties, GlobalClientInterceptorRegistry globalClientInterceptorRegistry) {
        return new AddressChannelFactory(gRpcChannelsProperties, globalClientInterceptorRegistry);
    }

    @Bean
    @ConditionalOnClass(GRpcClient.class)
    public GRpcClientBeanPostProcessor grpcClientBeanPostProcessor() {
        return new GRpcClientBeanPostProcessor();
    }

}

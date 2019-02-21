package cn.com.siss.spring.boot.grpc.autoconfigure;

import cn.com.siss.spring.boot.grpc.properties.GRpcChannelProperties;
import cn.com.siss.spring.boot.grpc.properties.GRpcChannelsProperties;
import com.google.common.collect.Lists;
import io.grpc.*;
import io.grpc.netty.NettyChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * AddressChannelFactory
 *
 * @author John Deng
 */
@Slf4j
public class AddressChannelFactory implements GRpcChannelFactory {
    private final GRpcChannelsProperties properties;
    private final GlobalClientInterceptorRegistry globalClientInterceptorRegistry;

    public AddressChannelFactory(GRpcChannelsProperties properties, GlobalClientInterceptorRegistry globalClientInterceptorRegistry) {
        this.properties = properties;
        this.globalClientInterceptorRegistry = globalClientInterceptorRegistry;
    }

    @Override
    public ManagedChannel createChannel(String name) {
        return this.createChannel(name, null);
    }

    @Override
    public ManagedChannel createChannel(String name, List<ClientInterceptor> interceptors) {
        GRpcChannelProperties channelProperties = properties.getChannel(name);
        String host = channelProperties.getServerHost();
        host = "".equals(host) ? name : host;
        Integer port = channelProperties.getServerPort();
        Boolean isEnableKeepAlive = channelProperties.isEnableKeepAlive();
        Long keyAliveDelay = channelProperties.getKeepAliveDelay();

        ManagedChannel channel = NettyChannelBuilder.forAddress(host, port)
                .usePlaintext(channelProperties.isPlaintext())
                .enableKeepAlive(isEnableKeepAlive, keyAliveDelay, TimeUnit.SECONDS, channelProperties.getKeepAliveTimeout(), TimeUnit.SECONDS)
                .build();

        if ((null != channel) && !channel.isTerminated() && !channel.isShutdown()) {
            log.info("gRPC channel - connect to server host: {}, port: {}", host, port);
            log.info("gRPC channel - keep alive : {}, timeout: {} seconds", isEnableKeepAlive ? "yes" : "no", keyAliveDelay);
        }

        List<ClientInterceptor> globalInterceptorList = globalClientInterceptorRegistry.getClientInterceptors();
        Set<ClientInterceptor> interceptorSet = new HashSet<>();
        if (globalInterceptorList != null && !globalInterceptorList.isEmpty()) {
            interceptorSet.addAll(globalInterceptorList);
        }
        if (interceptors != null && !interceptors.isEmpty()) {
            interceptorSet.addAll(interceptors);
        }
        return (ManagedChannel)ClientInterceptors.intercept(channel, Lists.newArrayList(interceptorSet));
    }
}


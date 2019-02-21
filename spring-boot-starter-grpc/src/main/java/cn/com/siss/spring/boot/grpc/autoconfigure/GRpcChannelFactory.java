package cn.com.siss.spring.boot.grpc.autoconfigure;


import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;

import java.util.List;

/**
 * GRpcChannelFactory
 *
 * @author John Deng
 */
public interface GRpcChannelFactory {

    ManagedChannel createChannel(String name);

    ManagedChannel createChannel(String name, List<ClientInterceptor> interceptors);
}

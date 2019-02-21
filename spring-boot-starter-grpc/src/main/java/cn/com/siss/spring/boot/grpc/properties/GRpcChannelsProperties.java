package cn.com.siss.spring.boot.grpc.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * GRpcChannelsProperties
 *
 * @author John Deng
 */
@Data
@ConfigurationProperties("grpc")
public class GRpcChannelsProperties {

    private Map<String, GRpcChannelProperties> client = new HashMap<>();

    public GRpcChannelProperties getChannel(String name) {
        return client.getOrDefault(name, GRpcChannelProperties.DEFAULT);
    }
}

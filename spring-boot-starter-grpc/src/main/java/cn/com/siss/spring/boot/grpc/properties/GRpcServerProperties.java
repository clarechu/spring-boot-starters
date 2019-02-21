package cn.com.siss.spring.boot.grpc.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * GRpcServerProperties
 *
 * @author John Deng
 */
@Data
@ConfigurationProperties("grpc.server")
public class GRpcServerProperties {
    /**
     * gRPC server port. Defaults to 7575
     */
    private int port = 7575;

    /**
     * Bind address for the server. Defaults to 0.0.0.0.
     */
    private String address = "0.0.0.0";

    private Boolean usePlainText = true;

}

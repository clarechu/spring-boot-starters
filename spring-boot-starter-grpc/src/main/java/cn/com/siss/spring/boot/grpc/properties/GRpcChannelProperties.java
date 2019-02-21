package cn.com.siss.spring.boot.grpc.properties;

import lombok.Data;

/**
 * GRpcChannelProperties
 *
 * @author John Deng
 */
@Data
public class GRpcChannelProperties {

    public static final String GRPC_DEFAULT_HOST = "";
    public static final Integer GRPC_DEFAULT_PORT = 7575;

    public static final GRpcChannelProperties DEFAULT = new GRpcChannelProperties();

    private String serverHost = GRPC_DEFAULT_HOST;

    private Integer serverPort = GRPC_DEFAULT_PORT;

    private boolean plaintext = true;

    private boolean enableKeepAlive = true;

    /**
     * The default delay in seconds before we send a keep alive.
     */
    private long keepAliveDelay = 10;

    /**
     * The default timeout in seconds for a keep alive ping request.
     */
    private long keepAliveTimeout = 120;
}

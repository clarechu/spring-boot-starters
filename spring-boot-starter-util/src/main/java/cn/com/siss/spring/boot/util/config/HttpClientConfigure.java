package cn.com.siss.spring.boot.util.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class HttpClientConfigure {

    public static CloseableHttpClient closeableHttpClient;

    private static final Integer ConnectionTimeout = 60000;

    private static final Integer ConnectionRequestTimeout = 10000;

    private static final Integer SocketTimeout = 60000;

    private static final Integer MaxIdleTime = 30;

    private static final String MimeType = "application/x-www-form-urlencoded";

    @Bean
    public CloseableHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //https config
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext
                , null, null, NoopHostnameVerifier.INSTANCE);

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", csf)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SocketTimeout)
                .setConnectTimeout(ConnectionTimeout)
                .setConnectionRequestTimeout(ConnectionRequestTimeout)
                .build();
        closeableHttpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .evictExpiredConnections()
                .evictIdleConnections(MaxIdleTime, TimeUnit.SECONDS)
                .build();
        return closeableHttpClient;
    }

}

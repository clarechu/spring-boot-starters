package cn.com.siss.spring.boot.grpc.annotations;

import io.grpc.ClientInterceptor;

import java.lang.annotation.*;

/**
 * GRpcClient
 * Marks the annotated class to be registered as grpc-client bean;
 *
 * @author John Deng
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface GRpcClient {

    String value() default "localhost";

    Class<? extends ClientInterceptor>[] interceptors() default {};
}

package cn.com.siss.spring.boot.grpc.annotations;

import io.grpc.ServerInterceptor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * GRpcService
 * Marks the annotated class to be registered as grpc-service bean;
 *
 * @author John Deng
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface GRpcService {
    Class<? extends ServerInterceptor>[] interceptors() default {};

    boolean applyGlobalInterceptors() default true;
}

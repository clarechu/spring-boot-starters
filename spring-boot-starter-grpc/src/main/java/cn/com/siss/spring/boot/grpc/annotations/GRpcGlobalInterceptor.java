package cn.com.siss.spring.boot.grpc.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * GRpcGlobalInterceptor
 *
 * @author John Deng
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GRpcGlobalInterceptor {
}

package cn.com.siss.spring.boot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpringBootStarterRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterRedisApplication.class, args);
    }

}

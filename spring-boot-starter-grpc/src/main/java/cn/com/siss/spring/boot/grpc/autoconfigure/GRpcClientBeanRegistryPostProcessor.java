package cn.com.siss.spring.boot.grpc.autoconfigure;

import io.grpc.ManagedChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.Map;

/**
 * Created by johnd on 3/21/17.
 */
public class GRpcClientBeanRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private Map<String, ManagedChannel> serviceBeans;

    public GRpcClientBeanRegistryPostProcessor(Map<String, ManagedChannel> serviceBeans) {
        this.serviceBeans = serviceBeans;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
            throws BeansException {

        for (Map.Entry<String, ManagedChannel> entry : serviceBeans.entrySet()) {
            RootBeanDefinition serviceDefinition = new RootBeanDefinition(ManagedChannel.class); //The service implementation
//            serviceDefinition.setTargetType(ManagedChannel.class); //The service interface
            serviceDefinition.setRole(BeanDefinition.ROLE_APPLICATION);
            serviceDefinition.setSource(entry.getValue());
            registry.registerBeanDefinition(entry.getKey(), serviceDefinition );
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // set properties here
    }
}
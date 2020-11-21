package io.github.harvies.charon.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetSocketAddress;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(value = CanalConnector.class)
@Setter
@Slf4j
public class CanalAutoConfiguration implements BeanDefinitionRegistryPostProcessor, EnvironmentPostProcessor {

    private static MultipleDestinationProperties multipleDestinationProperties = null;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("加载canal多数据源配置 begin");
        multipleDestinationProperties = Binder.get(environment).bind("charon.canal", MultipleDestinationProperties.class).get();
        log.info("加载canal多数据源配置 end data:[{}]", multipleDestinationProperties);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Map<String, MultipleDestinationProperties.Destination> destinations = multipleDestinationProperties.getDestinations();
        for (String destination : destinations.keySet()) {
            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(multipleDestinationProperties.getHost(),
                    multipleDestinationProperties.getPort()), destination, null, null);
            DestinationContext destinationContext = new DestinationContext();
            destinationContext.setDestination(destination);
            destinationContext.setFilter(destinations.get(destination).getFilter());
            destinationContext.setCanalConnector(connector);
            DestinationContext.destinationContextMap.put(destination, destinationContext);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}

package io.github.harvies.charon.mongo;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.Map;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(value = SimpleMongoClientDatabaseFactory.class)
@Setter
@Slf4j
public class MongoAutoConfiguration implements BeanDefinitionRegistryPostProcessor, EnvironmentPostProcessor {

    private static MultipleDataSourcesProperties multipleDataSourcesProperties = null;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("加载mongo多数据源配置 begin");
        multipleDataSourcesProperties = Binder.get(environment).bind("spring.data.mongodb", MultipleDataSourcesProperties.class).get();
        log.info("加载mongo多数据源配置 end data:[{}]", multipleDataSourcesProperties);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Map<String, MongoProperties> dataSources = multipleDataSourcesProperties.getDataSources();
        for (Map.Entry<String, MongoProperties> stringMongoPropertiesEntry : dataSources.entrySet()) {
            String dataSourceName = stringMongoPropertiesEntry.getKey();
            MongoProperties mongoProperties = stringMongoPropertiesEntry.getValue();

            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
            genericBeanDefinition.setBeanClass(MongoTemplate.class);
            genericBeanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            genericBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);
            constructorArgumentValues.addGenericArgumentValue(new SimpleMongoClientDatabaseFactory(mongoProperties.getUri()));
            String mongoTemplateBeanName = dataSourceName + "MongoTemplate";
            beanDefinitionRegistry.registerBeanDefinition(mongoTemplateBeanName, genericBeanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}

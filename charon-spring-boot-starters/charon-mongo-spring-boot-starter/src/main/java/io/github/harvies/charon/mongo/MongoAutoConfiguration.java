package io.github.harvies.charon.mongo;

import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(value = SimpleMongoClientDatabaseFactory.class)
@EnableConfigurationProperties({MultipleDataSourcesProperties.class})
@Setter
public class MongoAutoConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private MultipleDataSourcesProperties multipleDataSourcesProperties;

    @PostConstruct
    public void init() {
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) this.applicationContext;

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
}

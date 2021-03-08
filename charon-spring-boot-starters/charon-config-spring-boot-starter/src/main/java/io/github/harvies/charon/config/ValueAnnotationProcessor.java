package io.github.harvies.charon.config;

import io.github.harvies.charon.util.StringUtils;
import io.github.harvies.charon.util.reflect.FieldUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class ValueAnnotationProcessor implements BeanPostProcessor {

    private static final String SCOPED_TARGET_PREFIX = "scopedTarget.";

    @Resource
    private ConfigurableBeanFactory configurableBeanFactory;

    public static final Set<Object> set = new HashSet<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.startsWith(beanName, SCOPED_TARGET_PREFIX)) {
            return bean;
        }
        Field[] allFields = FieldUtils.getAllFields(bean.getClass());
        for (Field field : allFields) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                set.add(bean);
            }
        }
        return bean;
    }

    public void processor() {
        for (Object bean : set) {
            Field[] allFields = FieldUtils.getAllFields(bean.getClass());
            for (Field field : allFields) {
                Value annotation = field.getAnnotation(Value.class);
                if (annotation != null) {
                    String value = configurableBeanFactory.resolveEmbeddedValue(annotation.value());
                    try {
                        FieldUtils.writeField(field, bean, value, true);
                    } catch (IllegalAccessException e) {
                        log.warn("processor error field:[{}]", field, e);
                    }
                }
            }
        }
    }
}

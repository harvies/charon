package io.github.harvies.charon.spring.boot.core;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class TimeCostBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Long> costMap = Maps.newConcurrentMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        costMap.put(beanName, System.currentTimeMillis());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Long start = costMap.get(beanName);
        if (start == null) {
            return bean;
        }
        long cost = System.currentTimeMillis() - start;
        if (cost > 500) {
            costMap.put(beanName, cost);
            log.info("class [{}] bean:[{}] 加载耗时:[{}] ms", bean.getClass(), beanName, cost);
        }
        return bean;
    }
}

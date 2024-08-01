package spring.circular.dependencies;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BeanC {

    @Resource
    private BeanA beanA;

    @PostConstruct
    public void postConstruct(){
        beanA.testA();
    }
}

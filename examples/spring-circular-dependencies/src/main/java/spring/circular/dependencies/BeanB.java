package spring.circular.dependencies;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BeanB {
    @Resource
    private BeanA beanA;


    @PostConstruct
    public void init(){
        System.out.println("BeanB init");
    }
}

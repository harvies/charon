package spring.circular.dependencies;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BeanA {

//    @Lazy
    @Resource
    private BeanB beanB;


    @PostConstruct
    public void init(){
        System.out.println("BeanA init");
    }



    public void testA() {
        System.out.println("BeanA testA");
    }
}

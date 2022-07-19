package io.github.harvies.charon.mybatis.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import io.github.harvies.charon.mybatis.injector.methods.*;

import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new PutJson());
        methodList.add(new PutFeature());
        methodList.add(new PutJsons());
        methodList.add(new PutFeatures());
        methodList.add(new GetJson());
        methodList.add(new GetFeature());
        methodList.add(new GetJsons());
        methodList.add(new GetFeatures());
        methodList.add(new RemoveJson());
        methodList.add(new RemoveJsons());
        return methodList;
    }
}
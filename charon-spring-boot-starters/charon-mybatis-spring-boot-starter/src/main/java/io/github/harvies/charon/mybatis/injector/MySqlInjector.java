package io.github.harvies.charon.mybatis.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import io.github.harvies.charon.mybatis.injector.methods.*;

import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new PutJson("putJson"));
        methodList.add(new PutJsons("putJsons"));
        methodList.add(new GetJson("getJson"));
        methodList.add(new GetJsons("getJsons"));
        methodList.add(new RemoveJson("removeJson"));
        methodList.add(new RemoveJsons("removeJsons"));
        return methodList;
    }
}
package io.github.harvies.charon.jdbc.mybatis.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import io.github.harvies.charon.jdbc.mybatis.enums.MySqlMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据id删除json字段某个key
 */
public class RemoveJson extends AbstractMethod {
    public RemoveJson(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        MySqlMethod sqlMethod = MySqlMethod.REMOVE_JSON;
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                tableInfo.getKeyColumn(), tableInfo.getKeyProperty());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
    }
}
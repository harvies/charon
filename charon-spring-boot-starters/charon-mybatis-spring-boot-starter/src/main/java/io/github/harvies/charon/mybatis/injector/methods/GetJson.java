package io.github.harvies.charon.mybatis.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import io.github.harvies.charon.mybatis.enums.MySqlMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据id和key从json字段查询数据
 */
public class GetJson extends AbstractMethod {
    public GetJson(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        MySqlMethod sqlMethod = MySqlMethod.GET_JSON;
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                tableInfo.getKeyColumn(), tableInfo.getKeyProperty());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, sqlMethod.getMethod(), sqlSource, Object.class);
    }
}
package io.github.harvies.charon.mybatis.plugins;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.*;

/**
 * Sql打印插件
 *
 * @author harvies
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
@Slf4j
public class SqlPrintInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long beginTime = System.currentTimeMillis();
        /**
         * boundSqlSql中占位符需要替换成的参数
         */
        ArrayList<Object> paramList = Lists.newArrayList();
        BoundSql boundSql = ((RoutingStatementHandler) invocation.getTarget()).getBoundSql();
        String boundSqlSql = boundSql.getSql();
        try {
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                return null;
            }
            Class<?> parameterObjectClass = parameterObject.getClass();
            Field[] declaredFields = parameterObjectClass.getDeclaredFields();
            if (isPrimitive(parameterObjectClass) || parameterObject instanceof String) {
                /**
                 * 如果是基本数据类型或者String
                 */
                paramList.add(parameterObject);
            } else if (parameterObject instanceof Map) {
                /**
                 * 如果是Map类型
                 */
                for (ParameterMapping parameterMapping :
                        parameterMappingList) {
                    Object parameterValue = boundSql.getAdditionalParameter(parameterMapping.getProperty());
                    if (parameterValue == null) {
                        /**
                         * forEach加其他类型
                         */
                        parameterValue = ((Map) parameterObject).get(parameterMapping.getProperty());

                    }
                    paramList.add(parameterValue);
                }
            } else if (declaredFields.length > 0) {
                /**
                 * 是一个JavaBean
                 */
                for (ParameterMapping parameterMapping :
                        parameterMappingList) {
                    String property = parameterMapping.getProperty();
                    Object value = getPropertyValue(parameterObject, property, parameterObjectClass);
                    paramList.add(value);
                }
            } else {
                log.warn("error:{}", parameterObjectClass);
            }

            /**
             * 替换?为对应参数
             */
            for (int i = 0; i < paramList.size(); i++) {
                Object o = paramList.get(i);
                if (o instanceof Number) {
                    boundSqlSql = boundSqlSql.replaceFirst("\\?", String.valueOf(o));
                } else if (o instanceof Date) {
                    String format = DateFormatUtils.format((Date) o, "yyyy-MM-dd HH:mm:ss:sss");
                    boundSqlSql = boundSqlSql.replaceFirst("\\?", format);
                } else {
                    boundSqlSql = boundSqlSql.replaceFirst("\\?", "'" + o + "'");
                }
            }

        } finally {
            Object proceed = invocation.proceed();
            Integer resultNum = null;
            if (proceed instanceof Number) {
                resultNum = ((Number) proceed).intValue();
            } else if (proceed instanceof Collection) {
                resultNum = ((Collection) proceed).size();
            }
            log.warn("最终执行Sql:{} resultNum:{} 耗时:{} ms", boundSqlSql.replaceAll("[\\s\n ]+", " "), resultNum, System.currentTimeMillis() - beginTime);
            return proceed;
        }
    }


    /**
     * 判断一个对象是否是基本类型或基本类型的封装类型
     *
     * @param parameterObjectClass
     * @return
     */
    private boolean isPrimitive(Class<?> parameterObjectClass) {
        try {
            return ((Class<?>) parameterObjectClass.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    private Object getPropertyValue(Object object, String propertyName, Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder methodName = new StringBuilder();
        methodName.append("get")
                .append(propertyName.substring(0, 1).toUpperCase())
                .append(propertyName.substring(1));
        Method method = clazz.getMethod(methodName.toString());
        return method.invoke(object);
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}

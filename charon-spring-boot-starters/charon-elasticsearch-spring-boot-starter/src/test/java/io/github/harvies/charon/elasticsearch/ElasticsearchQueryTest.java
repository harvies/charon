package io.github.harvies.charon.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.github.harvies.charon.util.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.adapter.elasticsearch.ElasticsearchSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

@Slf4j
public class ElasticsearchQueryTest {
    Properties info;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Test
    public void testSelect() throws Exception {
        // 构造Schema
        RestClient restClient = RestClient
                .builder(new HttpHost(PropertiesUtils.getDefaultProperty("charon.elasticsearch.standalone.host"), Integer.parseInt(PropertiesUtils.getDefaultProperty("charon.elasticsearch.standalone.port"))))        // es默认9200端口
                .build();
        ElasticsearchSchema customerSchema = new ElasticsearchSchema(restClient, new ObjectMapper(), "customer");
        // 设置连接参数
        info = new Properties();
        info.setProperty("caseSensitive", "false");        // SQL大小写不敏感
        // 建立连接
        connection = DriverManager.getConnection("jdbc:calcite:", info);
        // 取得Calcite连接
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        // 取得RootSchema RootSchema是所有Schema的父Schema
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        // 添加schema
        rootSchema.add("es", customerSchema);
        // 编写SQL
        String sql = "select * from es.customer ";
        // 执行查询
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData md = resultSet.getMetaData();
        List<Map<String, Object>> resultList = Lists.newArrayList();
        while (resultSet.next()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {
                Map<String, Object> result = (Map<String, Object>) resultSet.getObject(i);
                resultList.add(result);
            }
        }
        log.info("resultList:[{}]", resultList);
        resultSet.close();
        statement.close();
        connection.close();
    }
}

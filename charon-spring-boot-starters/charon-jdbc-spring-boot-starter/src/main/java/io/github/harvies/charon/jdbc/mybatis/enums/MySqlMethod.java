package io.github.harvies.charon.jdbc.mybatis.enums;

public enum MySqlMethod {

    PUT_JSON("putJson", "根据id往json字段写入数据", "<script> UPDATE %s SET ${field} = json_set(IFNULL(${field},'{}'),'$.${key}', #{value}) WHERE %s=#{%s} </script>"),
    PUT_JSONS("putJsons", "根据id List往json字段写入多个key value数据", "" +
            "<script> UPDATE " +
            "            %s " +
            "        SET " +
            "            ${field} = json_set(IFNULL(${field},'{}'), " +
            "                    <foreach item=\"value\" index=\"key\" collection=\"values.entrySet()\" separator=\",\"> " +
            "                        CONCAT(\"$.\", JSON_QUOTE(\"${key}\")), #{value} " +
            "                    </foreach> " +
            "                ) " +
            "        WHERE " +
            "            %s in " +
            "                    <foreach item=\"value\" index=\"key\" collection=\"idList\" open=\"(\" close=\")\"  separator=\",\"> " +
            "                        '${value}'" +
            "                    </foreach> " +
            "; </script>"),
    GET_JSON("getJson", "根据id和key从json字段查询数据", "<script> SELECT JSON_EXTRACT(${field}, '$.${key}') FROM %s WHERE %s=#{%s} </script>"),
    GET_JSONS("getJsons", "根据id和keys从json字段查询数据", "<script> SELECT " +
            " <foreach item=\"value\" index=\"key\" collection=\"keys\" separator=\",\"> " +
            "    JSON_EXTRACT(${field}, '$.${value}') as ${value} " +
            " </foreach>" +
            " FROM %s WHERE %s=#{%s} </script>"),
    REMOVE_JSON("removeJson", "根据id删除json字段某个key", "<script> UPDATE %s SET ${field} = json_remove(IFNULL(${field},'{}'),'$.${key}') WHERE %s=#{%s} </script>"),

    REMOVE_JSONS("removeJsons", "根据id List 删除json字段多个key", "" +
            "<script> UPDATE " +
            "            %s " +
            "        SET " +
            "            ${field} = json_remove(IFNULL(${field},'{}'), " +
            "                    <foreach item=\"value\" index=\"key\" collection=\"keys\" separator=\",\"> " +
            "                        CONCAT(\"$.\", JSON_QUOTE(\"${value}\"))" +
            "                    </foreach> " +
            "                ) " +
            "        WHERE " +
            "            %s in " +
            "                    <foreach item=\"value\" index=\"key\" collection=\"idList\" open=\"(\" close=\")\"   separator=\",\"> " +
            "                        '${value}'" +
            "                    </foreach> " +
            "; </script>");


    private final String method;
    private final String desc;
    private final String sql;

    MySqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}

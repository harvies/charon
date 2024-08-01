package io.github.harvies.charon.jdbc.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseMapperPlus<T> extends BaseMapper<T> {

    /**
     * 根据id往json字段写入数据
     */
    int putJson(@Param(value = "field") String field, @Param(value = "id") Serializable id, @Param(value = "key") String key, @Param(value = "value") Object value);


    /**
     * 根据idList往json字段写入多个key value数据
     */
    int putJsons(@Param(value = "field") String field, @Param(value = "idList") Collection<? extends Serializable> idList, @Param(value = "values") Map<String, Object> values);


    /**
     * 根据id和key从json字段查询数据
     * todo 解决查询出来的值带有引号
     */
    @Deprecated
    Object getJson(@Param(value = "field") String field, @Param(value = "id") Serializable id, @Param(value = "key") String key);

    /**
     * 根据id和keys从json字段查询数据
     * todo 解决查询出来的值带有引号
     */
    @Deprecated
    Map<String, Object> getJsons(@Param(value = "field") String field, @Param(value = "id") Serializable id, @Param(value = "keys") List<String> keys);

    /**
     * 根据id往json字段写入数据
     */
    int removeJson(@Param(value = "field") String field, @Param(value = "id") Serializable id, @Param(value = "key") String key);

    /**
     * 根据idList往json字段写入多个key value数据
     */
    int removeJsons(@Param(value = "field") String field, @Param(value = "idList") Collection<? extends Serializable> idList, @Param(value = "keys") List<String> keys);


}
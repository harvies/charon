package io.github.harvies.eris.spring.namespace.data.mybatis.mapper;

import io.github.harvies.eris.spring.namespace.data.mybatis.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author harvies
 */
public interface UserMapper {
    User findById(Long id);

    int save(@Param("name") String name, @Param("age") Integer age, @Param("email") String email);
}

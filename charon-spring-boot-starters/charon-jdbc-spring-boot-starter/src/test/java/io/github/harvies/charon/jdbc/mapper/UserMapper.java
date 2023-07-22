package io.github.harvies.charon.jdbc.mapper;

import io.github.harvies.charon.jdbc.mybatis.mapper.BaseMapperPlus;
import io.github.harvies.charon.jdbc.po.UserPO;
public interface UserMapper extends BaseMapperPlus<UserPO> {

    void createTableIfNotExists();

    void truncateTable();

    void dropTableIfExists();
}

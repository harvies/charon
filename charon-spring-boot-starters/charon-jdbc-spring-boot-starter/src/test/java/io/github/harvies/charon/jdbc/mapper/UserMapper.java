package io.github.harvies.charon.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.harvies.charon.jdbc.po.UserPO;
public interface UserMapper extends BaseMapper<UserPO> {

    void createTableIfNotExists();

    void truncateTable();

    void dropTableIfExists();
}

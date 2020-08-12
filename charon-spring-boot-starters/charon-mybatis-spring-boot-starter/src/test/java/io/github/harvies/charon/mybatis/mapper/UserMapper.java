package io.github.harvies.charon.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.harvies.charon.mybatis.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author harvies
 * \@Repository 告诉IDEA这是一个Bean 防止报错
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}

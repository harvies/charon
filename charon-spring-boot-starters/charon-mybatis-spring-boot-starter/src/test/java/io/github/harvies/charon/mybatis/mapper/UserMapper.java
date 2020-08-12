package io.github.harvies.charon.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.harvies.charon.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author harvies
 * \@Repository 告诉IDEA这是一个Bean 防止报错
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User getById(@Param("id") Long id);

    User getById2(Long id);

    User getByName(@Param("name") String name);

    int save(User user);

    int deleteById(Long id);

    List<User> selectByIdList(@Param("idList") List<Long> idList);

    List<User> selectByIdListAndName(@Param("idList") List<Long> idList, @Param("name") String name);

}

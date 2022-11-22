package io.github.harvies.charon.mybatis.mapper;

import io.github.harvies.charon.mybatis.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author harvies
 * \@Repository 告诉IDEA这是一个Bean 防止报错
 */
@Repository
public interface UserMapper extends MyBaseMapper<UserPO> {

    UserPO getById(@Param("id") Long id);

    UserPO getById2(Long id);

    UserPO getByName(@Param("name") String name);

    int save(UserPO user);

    int deleteById(Long id);

    List<UserPO> selectByIdList(@Param("idList") List<Long> idList);

    List<UserPO> selectByIdListAndName(@Param("idList") List<Long> idList, @Param("name") String name);

}

package io.github.harvies.charon.mybatis;

import io.github.harvies.charon.mybatis.entity.User;
import io.github.harvies.charon.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Mybatis 中有一级缓存和二级缓存,默认情况下一级缓存是开启的,而且是不能关闭的。一级缓存
 * 是指 SqlSession 级别的缓存,当在同一个 SqlSession 中进行相同的 SQL 语句查询时,第二次以
 * 后的查询不会从数据库查询,而是直接从缓存中获取,一级缓存最多缓存 1024 条 SQL。二级缓存
 * 是指可以跨 SqlSession 的缓存。是 mapper 级别的缓存,对于 mapper 级别的缓存不同的
 * sqlsession 是可以共享的。
 * <p>
 * Mybatis 的一级缓存原理( sqlsession 级别 )
 * 第一次发出一个查询 sql,sql 查询结果写入 sqlsession 的一级缓存中,缓存使用的数据结构是一
 * 个 map。
 * key:MapperID+offset+limit+Sql+所有的入参
 * value:用户信息
 * 同一个 sqlsession 再次发出相同的 sql,就从缓存中取出数据。如果两次中间出现 commit 操作
 * (修改、添加、删除),本 sqlsession 中的一级缓存区域全部清空,下次再去缓存中查询不到所
 * 以要从数据库查询,从数据库查询到再写入缓存。
 *
 * @author harvies
 */
public class FirstCacheTest {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("test-mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            for (int i = 0; i < 10; i++) {
                User user = userMapper.getById(1L);
                System.err.println(user);
            }
        } finally {
            sqlSession.close();
        }
    }
}

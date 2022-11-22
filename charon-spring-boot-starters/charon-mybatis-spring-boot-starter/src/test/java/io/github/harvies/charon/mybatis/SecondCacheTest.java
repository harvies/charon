package io.github.harvies.charon.mybatis;

import io.github.harvies.charon.mybatis.po.UserPO;
import io.github.harvies.charon.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 二级缓存原理( mapper 基本 )
 * <p>
 * 二级缓存的范围是 mapper 级别(mapper 同一个命名空间),mapper 以命名空间为单位创建缓
 * 存数据结构,结构是 map。mybatis 的二级缓存是通过 CacheExecutor 实现的。CacheExecutor
 * 其实是 Executor 的代理对象。所有的查询操作,在 CacheExecutor 中都会先匹配缓存中是否存
 * 在,不存在则查询数据库。
 * key:MapperID+offset+limit+Sql+所有的入参
 * 具体使用需要配置:
 * 1. Mybatis 全局配置中启用二级缓存配置
 * 2. 在对应的 Mapper.xml 中配置 cache 节点
 * 3. 在对应的 select 查询节点中添加 useCache=true
 *
 * @author harvies
 */
public class SecondCacheTest {
    public static void main(String[] args) throws Exception {
        String resource = "test-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        for (int i = 0; i < 10; i++) {
            SqlSession session = sqlSessionFactory.openSession();
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);
                UserPO user = mapper.getById(1L);
                System.err.println(user);
                if (i == 5) {
                    UserPO user1 = new UserPO().setName("user" + i).setAge(i).setEmail("email" + i);
                    int save = mapper.save(user1);
                    System.err.println("save result:" + save);
                }
            } finally {
                session.commit();
                session.close();
            }
        }
    }
}

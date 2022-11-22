package io.github.harvies.charon.mybatis;

import io.github.harvies.charon.mybatis.po.UserPO;
import io.github.harvies.charon.mybatis.factory.ExampleObjectFactory;
import io.github.harvies.charon.mybatis.mapper.UserMapper;
import io.github.harvies.charon.mybatis.plugins.SqlPrintInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * mybatis拦截器测试
 *
 * @author harvies
 */
@Slf4j
public class TestPlugin {

    public static void main(String[] args) throws IOException {
        /**
         * new SqlSessionFactoryBuilder
         */
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        /**
         * new Configuration
         */
        Configuration configuration = new Configuration();
        /**
         * new Environment
         */
        Environment.Builder environmentBuild = new Environment.Builder("dev");
        /**
         * set DataSource
         */
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("test");
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:db/h2.sql'");

        environmentBuild.dataSource(dataSource);

        /**
         * set transactionFactory
         */
        environmentBuild.transactionFactory(new JdbcTransactionFactory());

        /**
         * build Environment
         */
        Environment environment = environmentBuild.build();
        /**
         * set Environment
         */
        configuration.setEnvironment(environment);

        /**
         * set ObjectFactory(optional)
         */
        configuration.setObjectFactory(new ExampleObjectFactory());

        /**
         * set underscoreToCamelCase
         */
        configuration.setMapUnderscoreToCamelCase(true);
        /**
         * set Log Impl(optional)
         */
        configuration.setLogImpl(Slf4jImpl.class);

        /**
         * add Interceptors
         */
        configuration.addInterceptor(new SqlPrintInterceptor());

        /**
         * add Mapper.xml
         */
        String mapperLocation = "mapper/UserMapper.xml";
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(Resources.getResourceAsStream(mapperLocation), configuration, mapperLocation, configuration.getSqlFragments());
        xmlMapperBuilder.parse();

        /**
         *  build sqlSessionFactory
         */
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(configuration);

        /**
         * openSession
         */
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            /**
             * getMapper
             */
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            /**
             * execute mapper
             */
            UserPO byId = userMapper.getById(1L);
            log.warn("byId:{}", byId);
            UserPO byId2 = userMapper.getById2(1L);
            log.warn("byId2:{}", byId2);

            UserPO user = userMapper.getByName("Jone");
            log.warn("user:{}", user);
            UserPO user1 = new UserPO().setName("aaa")
                    .setAge(1)
                    .setEmail("aaa@qq.com");
            userMapper.save(user1);
            log.warn("user1:{}", user1);
            int deleteById = userMapper.deleteById(user1.getId());
            log.warn("deleteById result:{}", deleteById);

            List<UserPO> userList = userMapper.selectByIdList(Arrays.asList(1L, 2L, 3L));
            log.warn("userList:{}", userList);
            List<UserPO> userList2 = userMapper.selectByIdListAndName(Arrays.asList(1L, 2L, 3L), "Jone");
            log.warn("userList2:{}", userList2);
        } finally {
            /**
             * close sqlSession
             */
            sqlSession.close();
        }

    }
}

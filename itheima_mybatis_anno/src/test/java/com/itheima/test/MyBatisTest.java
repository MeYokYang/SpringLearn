package com.itheima.test;


import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    private UserMapper mapper;

    @Before
    public void init() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);

    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("abc");
        mapper.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(11);
        user.setUsername("Lucy");
        user.setPassword("abc");
        mapper.update(user);
    }

    @Test
    public void testDelete(){
        mapper.delete(11);
    }

    @Test
    public void testFindById(){
        User user = mapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindAll(){
        List<User> users = mapper.findAll();
        System.out.println(users);
    }

}

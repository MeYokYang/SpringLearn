package com.itheima.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class JdbcTemplateTest {

    //测试JdbcTemplate开发步骤
    @Test
    public void test1() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql:///test");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        String sql = "INSERT INTO account VALUES(?, ?)";
        int row = jdbcTemplate.update(sql, "Tom", 5000);
        System.out.println(row);

    }

    //测试Spring产生JdbcTemplate对象
    @Test
    public void test2(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = app.getBean(JdbcTemplate.class);

        String sql = "INSERT INTO account VALUES(?, ?)";
        int row = jdbcTemplate.update(sql, "Mary", 5000);
        System.out.println(row);

    }

}

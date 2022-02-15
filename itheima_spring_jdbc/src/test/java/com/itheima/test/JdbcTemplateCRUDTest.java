package com.itheima.test;


import com.itheima.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate(){
        String sql = "UPDATE account SET money = ? WHERE name = ?";
        jdbcTemplate.update(sql, 2000, "Jack");
    }

    @Test
    public void testDelete(){
        String sql = "DELETE FROM account WHERE name = ?";
        jdbcTemplate.update(sql,  "Jack");
    }

    @Test
    public void testQueryAll(){
        String sql = "SELECT * FROM account";
        List<Account> accountList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
        System.out.println(accountList);
    }

    @Test
    public void testQueryOne(){
        String sql = "SELECT * FROM account WHERE name = ?";
        Account jack = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), "Jack");
        System.out.println(jack);
    }

    @Test
    public void testQueryCount(){
        String sql = "SELECT COUNT(*) FROM account";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

}

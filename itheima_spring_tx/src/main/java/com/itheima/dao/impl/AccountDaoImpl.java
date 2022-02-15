package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

/*
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
*/

    @Override
    public void out(String outMan, double money) {
        String sql = "UPDATE account SET money = money - ? WHERE name = ?";
        jdbcTemplate.update(sql, money, outMan);
    }

    @Override
    public void in(String inMan, double money) {
        String sql = "UPDATE account SET money = money + ? WHERE name = ?";
        jdbcTemplate.update(sql, money, inMan);
    }
}

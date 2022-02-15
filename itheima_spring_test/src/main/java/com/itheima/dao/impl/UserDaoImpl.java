package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM sys_user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    @Override
    public Long save(User user) {
        String sql = "INSERT INTO sys_user VALUES(?, ?, ?, ?, ?)";

//        jdbcTemplate.update(sql, null, user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());

        //创建PreparedStatementCreator
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                //使用原始的jdbc完成一个PreparedStatement的组建
                PreparedStatement preparedStatement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, null);
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getPhoneNum());
                return preparedStatement;
            }
        };
        //创建keyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(creator, keyHolder);

        //获得生成的主键
        long userId = keyHolder.getKey().longValue();

        return userId;
    }

    @Override
    public void saveUserRoleRel(Long userId, Long[] roleIds) {
        String sql = "INSERT INTO sys_user_role VALUES(?, ?)";
        for (Long roleId : roleIds) {
            jdbcTemplate.update(sql, userId, roleId);
        }
    }

    @Override
    public void delUserRoleRel(Long userId) {
        String sql = "DELETE FROM sys_user_role WHERE userId = ?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void del(Long userId) {
        String sql = "DELETE FROM sys_user WHERE id = ?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM sys_user WHERE username = ? AND password = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (Exception e) {
        }
        return user;
    }
}

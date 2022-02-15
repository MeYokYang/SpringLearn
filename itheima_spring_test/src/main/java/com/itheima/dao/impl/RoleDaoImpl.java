package com.itheima.dao.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM sys_role";
        List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
        return roleList;
    }

    @Override
    public void save(Role role) {
        String sql = "INSERT INTO sys_role VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, null, role.getRoleName(), role.getRoleDesc());
    }

    @Override
    public List<Role> findRoleByUserId(Long id) {
        String sql = "SELECT * FROM sys_user_role ur, sys_role r WHERE ur.roleId = r.id AND ur.userId = ?";
        List<Role> roles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class), id);
        return roles;
    }
}

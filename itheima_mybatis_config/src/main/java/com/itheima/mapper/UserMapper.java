package com.itheima.mapper;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    void save(User user);
    User findById(int id);
    List<User> findAll();

}

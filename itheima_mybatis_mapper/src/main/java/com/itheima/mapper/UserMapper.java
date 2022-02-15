package com.itheima.mapper;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findByCondition(User user);
    List<User> findByIds(List<Integer> Ids);
}

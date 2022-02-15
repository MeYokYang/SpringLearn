package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert("INSERT INTO user VALUES(#{id}, #{username}, #{password}, #{birthday})")
    void save(User user);

    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void delete(int id);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(
                    property = "orderList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.itheima.mapper.OrderMapper.findByUid")
            )
    })
    List<User> findUserAndOrderAll();


    @Select("SELECT * FROM user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(id = true, column = "username", property = "username"),
            @Result(id = true, column = "password", property = "password"),
            @Result(
                    property = "roleList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.itheima.mapper.RoleMapper.findByUid")
            )
    })
    List<User> findUserAndRoleAll();

}

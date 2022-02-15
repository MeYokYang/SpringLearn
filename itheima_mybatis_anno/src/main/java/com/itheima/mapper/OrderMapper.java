package com.itheima.mapper;

import com.itheima.domain.Order;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    /*@Select("SELECT *, o.id oid FROM orders o, user u WHERE o.uid = u.id")
    @Results({
            @Result(column = "oid", property = "id"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "total", property = "total"),
            @Result(column = "uid", property = "user.id"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "password", property = "user.password"),
    })
    List<Order> findAll();*/

    @Select("SELECT * FROM orders")
    @Results({
            @Result(column = "oid", property = "id"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "total", property = "total"),
            @Result(
                    property = "user", //要封装的属性名称是谁
                    column = "uid", //根据哪个字段去查询user表中的数据
                    javaType = User.class, //要封装的实体类型
                    //select属性 代表查询那个接口的方法获得数据
                    one = @One(select = "com.itheima.mapper.UserMapper.findById")
            )
    })
    List<Order> findAll();

    @Select("SELECT * FROM orders WHERE uid = #{uid}")
    List<Order> findByUid(int uid);

}

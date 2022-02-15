package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;


//<bean id="userService" class="com.itheima.service.impl.UserServiceImpl">
//@Component("userService")
@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {

    @Value("${jdbc.driver}")
    private String driver;

//    <property name="userDao" ref="userDao"/>
//    @Autowired //按照数据类型从Spring容器中进行匹配的
//    @Qualifier("userDao") //是按照id值从容器中匹配失误 但是此处@Qualifier结合@Autowired一起使用
    @Resource(name = "userDao") //相当于@Qualifier+@Autowired
    private UserDao userDao;

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public void save() {
        System.out.println(driver);
        userDao.save();
    }

    @PostConstruct
    public void init() {
        System.out.println("init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy...");
    }
}

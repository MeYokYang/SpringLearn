package com.itheima.factory;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;

public class StaticFactory {

    public static UserDao getUerDao(){
        return new UserDaoImpl();
    }

}


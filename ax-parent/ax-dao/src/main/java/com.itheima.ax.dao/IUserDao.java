package com.itheima.ax.dao;

import com.itheima.ax.dao.base.IBaseDao;
import com.itheima.ax.pojo.User;

public interface IUserDao extends IBaseDao<User>{
//    通过用户名和密码，
    public User findUserByUsernameAndPassword(String username,String password);
}

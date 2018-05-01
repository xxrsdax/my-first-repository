package com.itheima.ax.service;

import com.itheima.ax.pojo.User;

public interface IUserService  {

    public User login (User user);

    /**
     * 修改密码
     * */
    void editPassword(String id, String password);
}

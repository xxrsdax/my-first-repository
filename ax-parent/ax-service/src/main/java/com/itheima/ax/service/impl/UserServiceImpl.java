package com.itheima.ax.service.impl;

import com.itheima.ax.dao.IUserDao;
import com.itheima.ax.pojo.User;
import com.itheima.ax.service.IUserService;
import com.itheima.ax.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.awt.*;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource //注解注入
    private IUserDao userDao;

//    登录
    public User login(User user) {

        return   userDao.findUserByUsernameAndPassword(user.getUsername(), MD5Utils.md5(user.getPassword()));

    }
/**
 * 修改密码
 * */
    public void editPassword(String id, String password) {
//        调用工具类对密码进行加密
        password = MD5Utils.md5(password);
        userDao.excuteUpdate("user.editpassword", password,id);
    }
}

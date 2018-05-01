package com.itheima.ax.utils;


import com.itheima.ax.pojo.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * 自定义的工具类是，
 * */
public class AxUtils {

    /**
     * 获取session域对象
     * */
    public static  HttpSession getSession() {
        return  ServletActionContext.getRequest().getSession();
    }

    /**
     * 获取user对象
     * */
    public static  User getUser() {
        return  (User)getSession().getAttribute("loginUser");
    }

}

package com.itheima.ax.web.interceptor;

import com.itheima.ax.pojo.User;
import com.itheima.ax.utils.AxUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class AxLoginInterceptor extends MethodFilterInterceptor {
    /**
     * 自定义的拦截器
     * */
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        // 从session域中获取用户对象
//        User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        User loginUser = AxUtils.getUser();

        // 判断user对象是否为空
        if(loginUser == null) {
            //跳转到登录页面
            return "login";
        }

//     视频中的代码，学习
       return    actionInvocation.invoke();
    }
}

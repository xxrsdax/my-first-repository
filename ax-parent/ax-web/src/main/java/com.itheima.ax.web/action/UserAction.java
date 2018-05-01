package com.itheima.ax.web.action;

import com.itheima.ax.pojo.User;
import com.itheima.ax.service.IUserService;
import com.itheima.ax.utils.AxUtils;
import com.itheima.ax.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;

/**
 *用户Action   继承了BaseAction
 * */
@Controller
@Scope("prototype") //原型注解，扫描时不创建对象
public class UserAction extends BaseAction<User> {
    //属性驱动
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    @Resource
    private IUserService userService;

    public String login(){
        //1.获取验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
//        判断验证码是否正确
        if(StringUtils.isNotBlank(checkcode) && validatecode.equals(checkcode)) {
//           验证码正确
//           登录
            User user = userService.login(this.getModel()); //this.getModel 调用方法获取模型对象 User ，该方法在BaseAction中有实现
            if(user != null && StringUtils.isNotBlank(user.getId())) {
                ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
                return HOME;
            }else {
                this.addActionError("用户名或密码有误");
                return LOGIN;
            }
        }else {
//        验证码错误
            this.addActionError("验证码输入有误");
            return LOGIN;
        }

    }

    /**
     * 退出
     */
    public String logout(){

        ServletActionContext.getRequest().getSession().invalidate(); //手动销毁session

        return LOGIN;
    }

    /**
     * 修改密码
     * */
    public  String editPassword() {
        //设置标记变量
        String flag = "1";
        //获取参数
        String password = getModel().getPassword();
        //获取user对象
        User user = AxUtils.getUser();
        try{
            userService.editPassword(user.getId(),password); //无返回值
        }catch (Exception e){
            flag = "0";
            e.printStackTrace();
        }
//        将信息会写给ajax
        //设置会写参数的类型及编码
        ServletActionContext.getResponse().setContentType("txt/html;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().write(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;  //表示无返回值 ，新知道的东西
    }

}

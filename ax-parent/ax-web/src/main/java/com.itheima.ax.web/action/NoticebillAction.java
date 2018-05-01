package com.itheima.ax.web.action;

import com.itheima.ax.pojo.Customer;
import com.itheima.ax.pojo.Noticebill;
import com.itheima.ax.service.ICustomerService;
import com.itheima.ax.service.INoticebillService;
import com.itheima.ax.web.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 业务通知单
 * */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

    @Autowired
    private ICustomerService poxy;
    @Autowired
    private INoticebillService noticebillServiceimpl;

    /**
     *    根据电话查询用户信息  Query user information by telephone.
     **/
    public String findCustomerByTelephone(){
        String telephone = getModel().getTelephone();
        Customer customer = poxy.findCustomerByTelephone(telephone); //
        Object2Json(customer,new String[]{}); //其实个人觉得  telephone 不用转成json格式的数据
        return NONE;
    }

    /**
     * 添加业务通知单
     * */
    public String add() {
        //接收参数
        Noticebill noticebill = getModel();
        //调用方法保存用户
        noticebillServiceimpl.add(noticebill);
        return "noticebill_add"; //返回原页面
    }


}

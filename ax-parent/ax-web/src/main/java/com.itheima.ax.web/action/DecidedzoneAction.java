package com.itheima.ax.web.action;

import com.itheima.ax.pojo.Customer;
import com.itheima.ax.pojo.Decidedzone;

import com.itheima.ax.service.ICustomerService;
import com.itheima.ax.service.IDecidedzoneService;

import com.itheima.ax.web.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    @Autowired
    private IDecidedzoneService decidedzoneServiceImpl;

    //属性驱动
    private String[] subareaId;
    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }
    /**
     * 添加定区
     * */
    public String add() {

        decidedzoneServiceImpl.save(getModel(),subareaId);
        return LIST;
    }

    /**
     * 分页查询
     * */

//    Decidedzone_pageQuery   定区分页
    public String pageQuery() throws IOException {
         decidedzoneServiceImpl.pageQuery(pageBean);
        this.Object2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","decidedzones","subareas"});
        return NONE;
    }

//    DecidedzoneAction_findListNoAssociationDecidedzone

    @Autowired
    private ICustomerService poxy;  //生成 该接口的代理对象
/**
 * 查询所有未与定区关联的客户
 * */
    public String findListNoAssociationDecidedzone(){
        //使用ICustomerService
        List<Customer> customerList =  poxy.findListNoAssociationDecidedzone();
        Object2Json(customerList,new String[]{}); //排除指定字段 ,客户表与定区的数据库基本无实际关联
        return NONE;
    }

    //属性驱动
    private String decidedzoneId;

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }
    /**
     * 查询与指定 定区关联的客户
     * */
    public String findListAssociationDecidedzone() {
        List<Customer> customerList = poxy.findListAssociationDecidedzone(decidedzoneId);
        Object2Json(customerList,new String[]{});
        return NONE;
    }

    //属性驱动
    private List<String> customerIds;
    public void setCustomerIds(List<String> customerIds) {
        this.customerIds = customerIds;
    }
    public String assigncustomerstodecidedzone(){
        //将与指定定区关联的用户的定区清空
        poxy.emptyByDecidedzone(getModel().getId());
        //让指定id的用户，与指定定区关联
        poxy.updateByCustomerIds(customerIds,getModel().getId());
        return LIST;
    }

//    @Autowired
//    private ICustomerService poxy;
//    /**
//     * 查询全部    ,测试webservice 服务
//     * */
//    public String findAll(){
//
//        List<Customer> all = poxy.findAll();
//        for(Customer customer : all) {
//            System.out.println(customer);
//        }
//        return NONE;
//    }

}


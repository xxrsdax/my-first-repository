package com.itheima.ax.service.impl;

import com.itheima.ax.dao.IDecidedzoneDao;
import com.itheima.ax.dao.INoticebillDao;
import com.itheima.ax.dao.IWorkbillDao;
import com.itheima.ax.pojo.*;
import com.itheima.ax.service.ICustomerService;
import com.itheima.ax.service.INoticebillService;
import com.itheima.ax.utils.AxUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

    @Autowired
    private IDecidedzoneDao decidedzoneDaoImpl;
    @Autowired
    private ICustomerService poxy;
    @Autowired
    private INoticebillDao noticebillDaoImpl;
    @Autowired
    private IWorkbillDao workbillDaoImpl;
    /**
     * 保存一个业务通知单，并尝试自动分单 ,(创建)
     * */
    public void add(Noticebill noticebill) {
        User user = AxUtils.getUser();
        noticebill.setUser(user); //设置当前操作的用户，个人猜测，表示这业务通知单是谁接的
        noticebillDaoImpl.save(noticebill);
        //获取客户的取件地址
        String pickaddress = noticebill.getPickaddress();
        String decidedzoneId = poxy.findDecidedzoneIdByAddress(pickaddress); //根据用户取件地址获取定区id

        if(StringUtils.isNotBlank(decidedzoneId)) { //定区id是否真的取到
            Decidedzone decidedzone = decidedzoneDaoImpl.findById(decidedzoneId);
            Staff staff = decidedzone.getStaff();
            noticebill.setStaff(staff);//业务通知单关联取派员
            //设置分单类型
            noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO); //设置为自动分单
            //为取派员生成工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0); //设置追单次数
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis())); //设置工单生成时间   ，System.currentTimeMillis()  获取当前时间的毫秒值
            workbill.setNoticebill(noticebill); //让工单关联业务通知单
            workbill.setPickstate(Workbill.PICKSTATE_NO);//设置取件状态  ,设置为 未取件
            workbill.setRemark(noticebill.getRemark()); //设置备注
            workbill.setStaff(staff); //设置工单的取派员
            workbill.setType(Workbill.TYPE_1); //设置工单类型
            workbillDaoImpl.save(workbill); //保存工单

            //短信调用平台，发送短信

        }else {
            //设置人工分单
            noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN); //设置为人工分单
        }

        //自动分单的效果
    }
}

package com.itheima.ax.web.action;

import com.itheima.ax.pojo.Workordermanage;
import com.itheima.ax.service.IWorkordermanageService;
import com.itheima.ax.web.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

    @Autowired
    private IWorkordermanageService workordermanageServiceImpl;

    /**
     * 添加一个工作单
     * */
    public String add() throws IOException {
        String flag = "1";
        try{
            workordermanageServiceImpl.add(getModel());
        }catch(Exception e){
            e.printStackTrace();
            flag = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8"); //UTF-8
        ServletActionContext.getResponse().getWriter().write(flag);
        return NONE;
    }
}

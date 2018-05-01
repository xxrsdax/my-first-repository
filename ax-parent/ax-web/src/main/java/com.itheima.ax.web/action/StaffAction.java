package com.itheima.ax.web.action;

import com.itheima.ax.pojo.Staff;
import com.itheima.ax.service.IStaffService;
import com.itheima.ax.service.impl.StaffServiceImpl;
import com.itheima.ax.utils.PageBean;
import com.itheima.ax.web.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller  //表现层注解配置
@Scope("prototype") //配置为原型，及多例模式，多例模式只在调用时创建对象 注意：此处写错过
public class StaffAction extends BaseAction<Staff>{

    @Autowired
    public IStaffService staffServiceimpl;  //注解service


    /**
     * 添加派送员
     * */
    public String addStaff() {
        //1.获取Staff对象信息
        Staff staff = getModel();
        staffServiceimpl.addStaff(staff);
        return LIST;
    }


    /**
     * 派件员分页
     * */
    public String pageQuery() {
        staffServiceimpl.pageQuery(pageBean); //调用方法，进行分页查询
        this.Object2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
//        //调用json-lib 将数据转化为json格式的数据
//        //JSONObject --- 将对象转化为json格式数据
//        //JSONArray ----  将数组或集合转化为json格式数据
//        JsonConfig jsonConfig = new JsonConfig();
//        //指定不需要转化为json格式的数据
//        jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
//        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
//        //设置回写格式
//        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8"); //注意括号中的值
//        try {
//            //将数据回写
//            ServletActionContext.getResponse().getWriter().write(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return NONE;  //该方法是被ajax请求调用，不需要return
    }

    //属性驱动
    private String ids;

    /**
     *对派送员进行批量的逻辑删除
     * */
    public String deleteBatch(){
        staffServiceimpl.deleteBatch(ids);
        return LIST;
    }


/**
 * 更新派送员数据 ，
 * */
//表单提交过来的数据都被封装到了model中，但部分数据没有在更新的表单中，所以不能直接对象更新
    public String editStaff() {
        //根据id查询对象
        Staff model = getModel();
        Staff staff = staffServiceimpl.findById(model.getId());

        staff.setHaspda(model.getHaspda());
        staff.setName(model.getName());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staff.setTelephone(model.getTelephone());

        staffServiceimpl.update(staff); //更新派送员
        return LIST;
    }

//    StaffAction_listAjxa
    public String listAjxa() throws IOException {
        //查询所有没有被删除的取派员
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.ne("deltag","1"));
        List<Staff> staffList = staffServiceimpl.findByDeltag(detachedCriteria);

        //转成json格式的数据，会写
        Object2Json(staffList,new String[]{"telephone","haspda","deltag","station","standard","decidedzones"});

        return NONE;
    }


    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }


}

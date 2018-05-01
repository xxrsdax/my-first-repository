package com.itheima.ax.service.impl;

import com.itheima.ax.dao.IStaffDao;
import com.itheima.ax.pojo.Staff;
import com.itheima.ax.service.IStaffService;
import com.itheima.ax.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

    @Autowired
    public IStaffDao staffDaoImpl;
    /**
     * service 层添加派送员
     * */
    public void addStaff(Staff staff) {
        staffDaoImpl.save(staff);
    }

   /* *
    * service  业务层  派送员分页
    */
    public void pageQuery(PageBean pageBean) {
        staffDaoImpl.pageQuery(pageBean);
    }
 /**
  * 批量逻辑删除派送员
  * */
    public void deleteBatch(String ids) {
        if(ids != null ) {
            String[] idArray = ids.split(",");
            for(String id : idArray){
                staffDaoImpl.excuteUpdate("staff.delete",id);  //调用dao层方法，对派送员deltag字段进行更新 ，实现逻辑删除
            }
        }
    }

     /**
      * 根据id查询对象
      * */
    public Staff findById(String id) {
        return staffDaoImpl.findById(id);

    }

     /**
      * 更新派送员
      * */
    public void update(Staff staff) {
        staffDaoImpl.update(staff);
    }

    /**
     * 查询未被删除的取派员
     * */
    public List<Staff> findByDeltag(DetachedCriteria detachedCriteria) {

        return staffDaoImpl.find(detachedCriteria);
    }

}

package com.itheima.ax.service;

import com.itheima.ax.pojo.Staff;
import com.itheima.ax.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface IStaffService {
    /**
     * 添加派送员
     * */
    void addStaff(Staff staff);

    /**
     * 用于派件员的分页
     * */
    void pageQuery(PageBean pageBean);

    /**
     * 批量逻辑删除派送员
     * */
    void deleteBatch(String ids);

    /**
     * 根据id查询对象
     * */
    Staff findById(String id);

    /**
     * 更新派送员
     * */
    void update(Staff staff);

    /**
     * 查询未被删除的取派员
     * */
    List<Staff> findByDeltag(DetachedCriteria detachedCriteria);
}

package com.itheima.ax.service;

import com.itheima.ax.pojo.Subarea;
import com.itheima.ax.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ISubareaService {

    /**
     *  添加 subarea 对象
     * */
    void add(Subarea subarea);

    /*＊
    * 分页方法
    * */
    void pageQuery(PageBean pageBean);


    /**
     * 查询全部的Subarea对象
     * */
    List<Subarea> findAll();

    /**
     * 查询所有未被分配的分区
     * */
    List<Subarea> findlistByDecidedzoneNull(DetachedCriteria detachedCriteria);

    /**
     * 根据id查询Subarea对象
     * */
    Subarea findById(String id);

    /**
     * 查询与指定定区关联的分区
     * */
    List<Subarea> findListAssociationDecidedzone(DetachedCriteria detachedCriteria);
}

package com.itheima.ax.service.impl;

import com.itheima.ax.dao.ISubareaDao;
import com.itheima.ax.pojo.Subarea;
import com.itheima.ax.service.ISubareaService;
import com.itheima.ax.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl  implements ISubareaService{

    @Autowired
    private ISubareaDao subareaDaoImpl;

    /**
     *添加 subarea 对象
     * */
    public void add(Subarea subarea) {
        subareaDaoImpl.save(subarea);
    }

    /**
     * 分页查询
     * */
    public void pageQuery(PageBean pageBean) {
        subareaDaoImpl.pageQuery(pageBean);
    }

    /**
     * 查询全部Subarea对象
     * */
    public List<Subarea> findAll() {
        return subareaDaoImpl.findAll();
    }

    /**
     * 查询全部未被分配的分区
     * */
    public List<Subarea> findlistByDecidedzoneNull(DetachedCriteria detachedCriteria) {
        return subareaDaoImpl.find(detachedCriteria);
    }

    /**
     * 根据id获取subarea对象
     * */
    public Subarea findById(String id) {
        return subareaDaoImpl.findById(id);
    }

    /**
     * 查询与指定定区关联的分区
     * */
    public List<Subarea> findListAssociationDecidedzone(DetachedCriteria detachedCriteria) {
        return  subareaDaoImpl.find(detachedCriteria);
    }
}

package com.itheima.ax.dao.base;

import com.itheima.ax.pojo.Region;
import com.itheima.ax.pojo.Staff;
import com.itheima.ax.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 *  持久层通用接口
 *
 * @author ax
 * @param  <T>
 * */
public interface IBaseDao<T> {

    /**
     * 保存
     * */
    public  void save(T entity);

    /**
     * 删除
     * */
    public  void delete(T entity);

    /**
     * 更新
     * */
    public void  update(T entity);

    /**
     * 根据 id  查询对象
     * */                     //序列化
    public T findById(Serializable id);

    /**
     * 查询所有
     * */
    public List<T> findAll();

    /**
     * 执行所有更新
     * */
    void excuteUpdate(String s,Object... objects);

    /**
     * 通用分页
     * */
    void pageQuery(PageBean pageBean);

/**
 * 更新或保存
 * */
    void saveOrUpdate(T t);

    /**
     * 根据离线查询对象  ，执行查询
     * */
    List<T> find(DetachedCriteria detachedCriteria);
}

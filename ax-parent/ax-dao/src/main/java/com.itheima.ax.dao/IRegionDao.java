package com.itheima.ax.dao;

import com.itheima.ax.dao.base.IBaseDao;
import com.itheima.ax.pojo.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region>{

    /**
     * 根据条件查询Region
     * */
    List<Region> findList(String q);
}

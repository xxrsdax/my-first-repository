package com.itheima.ax.service;

import com.itheima.ax.pojo.Region;
import com.itheima.ax.utils.PageBean;

import java.util.List;

public interface IRegionService {

   /**
    * 批量保存
    * */
    void saveBatch(List<Region> regionList);

    /**
     * 分页查询
     * */
    void pageQuery(PageBean pageBean);

    /**
     * 查询所有 Region
     * */
    List<Region> findAll();

    /**
     * 跟据参数q ，查找Region
     **/
    List<Region> findListByQ(String q);
}

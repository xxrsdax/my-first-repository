package com.itheima.ax.service.impl;

import com.itheima.ax.dao.IRegionDao;
import com.itheima.ax.pojo.Region;
import com.itheima.ax.service.IRegionService;
import com.itheima.ax.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao regionDaoImpl;
  /**
   * 批量保存Region
   * */
    public void saveBatch(List<Region> regionList) {
        if(regionList != null && regionList.size() >0) {
            for (Region region : regionList){
                regionDaoImpl.saveOrUpdate(region);
            }
        }
    }

    /**
     * 分页查询
     * */
    public void pageQuery(PageBean pageBean) {
        regionDaoImpl.pageQuery(pageBean);
    }

  /**
   * 查询全部 region
   * */
    public List<Region> findAll() {
        return regionDaoImpl.findAll() ;
    }

    /**
     * 根据 参数q 查询 Region
     * */
    public List<Region> findListByQ(String q) {

       List<Region> regionList = regionDaoImpl.findList(q);
       return regionList;
    }
}

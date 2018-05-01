package com.itheima.ax.dao.impl;

import com.itheima.ax.dao.IRegionDao;
import com.itheima.ax.pojo.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository   //中文  仓库
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

    /**
     *  根据条件 查询 Region
     * */
    public List<Region> findList(  String q) {
        String sql = "from Region r where r.province like ? or r.city like ? or r.district like ? or r.shortcode like ? or r.citycode like ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(sql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");
        return list;
    }
}

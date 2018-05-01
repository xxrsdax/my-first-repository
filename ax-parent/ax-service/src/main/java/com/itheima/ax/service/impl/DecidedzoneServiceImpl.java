package com.itheima.ax.service.impl;

import com.itheima.ax.dao.IDecidedzoneDao;
import com.itheima.ax.dao.ISubareaDao;
import com.itheima.ax.pojo.Decidedzone;
import com.itheima.ax.pojo.Subarea;
import com.itheima.ax.service.IDecidedzoneService;
import com.itheima.ax.service.ISubareaService;
import com.itheima.ax.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

    @Autowired
    private IDecidedzoneDao decidedzoneDaoImpl;

    @Autowired
    private ISubareaDao subareaDaoImpl;

    /**
      * 添加定区
      * */
    public void add(Decidedzone model) {
        decidedzoneDaoImpl.save(model);
    }

     /**
      * 保存定区对象
      * */
    public void save(Decidedzone model, String[] subareaId) {
                //添加定区
        decidedzoneDaoImpl.save(model);

        //让分区关联定区，多方维护关系， 一方放弃维护关系
        if(subareaId != null && subareaId.length>0) {
            for(String  id: subareaId) {
                Subarea subarea = subareaDaoImpl.findById(id);
                subarea.setDecidedzone(model); //让
            }
        }
    }

    /**
     * 定区分页查询
     * */
    public void pageQuery(PageBean pageBean) {
        decidedzoneDaoImpl.pageQuery(pageBean);
    }


}

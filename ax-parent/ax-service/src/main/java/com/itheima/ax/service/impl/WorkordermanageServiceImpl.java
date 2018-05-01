package com.itheima.ax.service.impl;

import com.itheima.ax.dao.IWorkordermanageDao;
import com.itheima.ax.pojo.Workordermanage;
import com.itheima.ax.service.IWorkordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

    @Autowired
    private IWorkordermanageDao workordermanageDaoImpl;
    /**
     * 添加一个工作单
     * */
    public void add(Workordermanage model) {
        workordermanageDaoImpl.save(model);
    }
}

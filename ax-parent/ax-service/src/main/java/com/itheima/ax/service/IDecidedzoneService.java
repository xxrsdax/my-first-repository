package com.itheima.ax.service;

import com.itheima.ax.pojo.Decidedzone;
import com.itheima.ax.utils.PageBean;

public interface IDecidedzoneService {

    /**
     * 添加定区对象
     * */
    void add(Decidedzone model);
    /**
     * 保存定区对象
     * */
    void save(Decidedzone model, String[] subareaId);

    /**
     * 定区分页
     * */
    void pageQuery(PageBean pageBean);


}

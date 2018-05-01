package com.itheima.ax.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;
/**
 * PageBean  分页工具类
 * */
public class PageBean {

    private int currentPage;//当前页数
    private int pageSize;  //每页显示的行数
    private DetachedCriteria detachedCriteria; //离线查询对象，用来接收查询参数
    private Long total; //总记录数
    private List rows; //当页显示的记录数

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

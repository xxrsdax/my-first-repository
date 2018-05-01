package cn.itheima.mybatis.mapper;

import cn.itheima.mybatis.pojo.Orders;

import java.util.List;

public interface OrdersMapper  {

//    查询全部
    public List<Orders> findOrder();


/**
 * 一对一关联查询
 * */
public List<Orders> selectOrders();
}

package com.shumei.dao;

import com.shumei.pojo.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrderList(); // 获取所有订单列表

    boolean dorder(int oid);    //根据订单ID删除订单

    void updateOrder(Order order);  //更新订单信息

    void addOrder(Order order); //添加新订单

    Order getOrderByPid(Integer oid);   //根据订单ID获取订单信息

    List<Order> getOrderList(String keyword);   //根据关键词获取订单列表


}

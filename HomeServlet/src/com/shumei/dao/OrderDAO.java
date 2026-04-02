package com.shumei.dao;

import com.shumei.pojo.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrderList();

    boolean dorder(int oid);

    void updateOrder(Order order);

    void addOrder(Order order);

    Order getOrderByPid(Integer oid);

    List<Order> getOrderList(String keyword);


}

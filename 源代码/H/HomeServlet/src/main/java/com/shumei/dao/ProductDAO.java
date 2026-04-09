package com.shumei.dao;

import com.shumei.pojo.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getproductList(); //获取所有产品列表
    boolean delproduct(int pid);    //根据产品ID删除产品
    void addProduct(Product product);   //添加新产品
    void updateProduct(Product product);    //更新产品信息
    Product getProductByPid(Integer pid);   //根据产品ID获取产品信息
    List<Product> getproductList(String keyword);   //根据关键词获取产品列表
}

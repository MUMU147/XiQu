package com.shumei.dao;

import com.shumei.pojo.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getproductList();
    boolean delproduct(int pid);
    void addProduct(Product product);
    void updateProduct(Product product);
    Product getProductByPid(Integer pid);
    List<Product> getproductList(String keyword);
}

package com.shumei.servlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Product;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class Shop extends ViewBaseServlet{
//    展示部分数据
//    从数据库中获取一部分数据 给 ul li添加 value值为获取数据库的关键字
//    List<product> getproductList(String keyword)
//    渲染到页面
    ProductDAO productDAO=new ProductDAOImpl();
    List<Product> productList=productDAO.getproductList();
}

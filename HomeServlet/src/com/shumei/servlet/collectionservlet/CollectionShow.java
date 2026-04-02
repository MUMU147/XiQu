package com.shumei.servlet.collectionservlet;

import com.shumei.dao.CollectionDAO;
import com.shumei.dao.impl.CollectionDAOImpl;
import com.shumei.pojo.Product;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mycollect")
public class CollectionShow extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        先判断是否有用户登录
//        如果没有登录则跳转到登录页面
//        如果已经登陆，则CollectionDAO中调用查找的方法List<product> myCollection(User user),把查询结果返回
//        查询结果显示到某个页面 mycollection.html
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user==null){
//            未登录
            super.processTemplate("foreground/login",request,response);
        }
        else {
//            已经登陆
            CollectionDAO collectionDAO=new CollectionDAOImpl();
            List<Product> productList=collectionDAO.myCollection(user);
            request.setAttribute("productx",productList);
            super.processTemplate("foreground/mycollection",request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

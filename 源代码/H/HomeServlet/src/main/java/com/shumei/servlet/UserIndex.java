package com.shumei.servlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Product;
import com.shumei.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/uindex")
public class UserIndex extends ViewBaseServlet{
    private   ProductDAO productDAO=new ProductDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        取session，判断是否已经登录，如果登录，else的话 调 到登录页面
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
        request.setCharacterEncoding("utf-8");
        String searchone=request.getParameter("searchone");
        String searchtwo=request.getParameter("searchtwo");
        if("查询".equals(searchone)){
            //        获取表单数据
            String keyword="";
            keyword=request.getParameter("keyword");
//创建对象
            List<Product> list=productDAO.getproductList(keyword);
            System.out.println(list.size());
            request.setAttribute("products",list);
            super.processTemplate("index",request,response);
        }
        else if("显示所有".equals(searchtwo)){
            //        获取表单数据
            String keyword="";
            keyword=request.getParameter("keyword");
//创建对象
            List<Product> list=productDAO.getproductList(keyword);
            System.out.println(list.size());
            request.setAttribute("products",list);
            super.processTemplate("foreground/showproduct",request,response);
        }
        else {
            super.processTemplate("foreground/proadd", request, response);
        }
    }
    }
}


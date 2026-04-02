package com.shumei.servlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/showindex")
public class sIndex extends ViewBaseServlet{
    private ProductDAO productDAO=new ProductDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String shipin= request.getParameter("shipin");
        String nianhuo= request.getParameter("nianhuo");
        String wenju=request.getParameter("wenju");
        System.out.println(shipin);
        if("饰".equals(shipin)){
            //        获取表单数据
            String keyword="饰";
//创建对象
            List<Product> list=productDAO.getproductList(keyword);
            System.out.println(list.size());
            request.setAttribute("products",list);
            super.processTemplate("sindex",request,response);
            System.out.println(list);
        }
        else if("年".equals(nianhuo)){
            String keyword="年";
            List<Product> list=productDAO.getproductList(keyword);
            System.out.println(list.size());
            request.setAttribute("products",list);
            super.processTemplate("sindex",request,response);
            System.out.println(list);
        }else if("文具".equals(wenju)){
            String keyword="文具";
            List<Product> list=productDAO.getproductList(keyword);
            System.out.println(list);
            System.out.println(list.size());
            request.setAttribute("products",list);
            super.processTemplate("sindex",request,response);
        }
    }
}

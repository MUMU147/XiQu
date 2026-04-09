package com.shumei.servlet.productservlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.pojo.Product;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mypindex")
public class MproductSelectThy extends ViewBaseServlet {
private  ProductDAO productDAO=new ProductDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            String searchone = request.getParameter("searchone");
            String searchtwo = request.getParameter("searchtwo");
            if ("查询".equals(searchone)) {
                //        获取表单数据
                String keyword = "";
                keyword = request.getParameter("keyword");
//创建对象
                List<Product> list = productDAO.getproductList(keyword);
                System.out.println(list.size());
                request.setAttribute("products", list);
                super.processTemplate("admin/index", request, response);
            } else if ("显示所有".equals(searchtwo)) {
                //        获取表单数据
                String keyword = "";
                keyword = request.getParameter("keyword");
//创建对象
                List<Product> list = productDAO.getproductList(keyword);
                System.out.println(list.size());
                request.setAttribute("products", list);
                super.processTemplate("admin/index", request, response);
            } else {
                super.processTemplate("admin/proadd", request, response);
            }
        }else {
            super.processTemplate("admin/login",request,response);
        }
    }
}
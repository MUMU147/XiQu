package com.shumei.servlet.userservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.Product;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myuindex")
public class UserSelectThy extends ViewBaseServlet {
private UserDAO userDAO=new UserDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String searchone=request.getParameter("searchone");
        String searchtwo=request.getParameter("searchtwo");
        if("查询".equals(searchone)){
            //        获取表单数据
            String keyword="";
            keyword=request.getParameter("keyword");
//创建对象
            List<User> list=userDAO.getUserList(keyword);
            System.out.println(list.size());
            request.setAttribute("users",list);
            super.processTemplate("index",request,response);
        }
        else if("显示所有".equals(searchtwo)){
            //        获取表单数据
            String keyword="";
            keyword=request.getParameter("keyword");
//创建对象
            List<User> list=userDAO.getUserList(keyword);
            System.out.println(list.size());
            request.setAttribute("users",list);
            super.processTemplate("index",request,response);
        }else {
            super.processTemplate("useradd", request, response);
        }
    }
}
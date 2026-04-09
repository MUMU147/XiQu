package com.shumei.servlet.userservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/useredit")
public class UserEdit extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user);
            super.processTemplate("admin/login", request, response);
        }else {
            response.setContentType("text/html;charset=utf-8");
//            跳到登录页面
            response.getWriter().print("<script language='javascript'>alert('用户未登录');</script>");
            response.getWriter().println("<script>window.location='adminlogin'</script>");
        }
    }
}

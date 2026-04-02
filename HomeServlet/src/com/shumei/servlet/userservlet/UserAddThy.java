package com.shumei.servlet.userservlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.pojo.Product;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;
import com.shumei.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/museradd")
@MultipartConfig
public class UserAddThy extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request,response);
        super.processTemplate("useradd",request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
//        获取表单数据
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String nickname=request.getParameter("nickname");

            if (Util.isEmpty(username) || Util.isEmpty(password) ) {
                throw new RuntimeException("输入非法！！！");
            }
            User user = new User(username, password,email,nickname);
            UserDAO userDAO = new UserDAOImpl();
            userDAO.addUser(user);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>alert('添加成功');</script>");
            response.getWriter().println("<script>window.location='pindex'</script>");
        }
        else {
            super.processTemplate("login",request,response);
        }
    }
}
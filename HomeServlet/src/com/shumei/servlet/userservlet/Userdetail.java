package com.shumei.servlet.userservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/userdetail")
public class Userdetail extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO=new UserDAOImpl();
        int uid= Integer.parseInt(request.getParameter("uid"));
        User user =userDAO.getuserByUid(uid);
        request.setAttribute("user", user);
        super.processTemplate("userdetail",request,response);
    }
}

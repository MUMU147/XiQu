package com.shumei.servlet.userservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/muserdel")
public class UserDeleteThy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO=new UserDAOImpl();
        int uid= Integer.parseInt(request.getParameter("uid"));
        userDAO.duser(uid);
        response.sendRedirect("pindex");
    }
}
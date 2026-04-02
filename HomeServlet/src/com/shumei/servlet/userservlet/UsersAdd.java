package com.shumei.servlet.userservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/useradd")
public class UsersAdd extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uname=request.getParameter("uname");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String nickname=request.getParameter("nickname");
        System.out.println(uname+password+email+nickname);

        User user = new User(uname,password,email,nickname);
        UserDAO userDAO=new UserDAOImpl();
        userDAO.addUser(user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

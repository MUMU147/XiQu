package com.shumei.servlet.user_foregroundservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/usere")
public class EditUser extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
this.doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        UserDAO userDAO=new UserDAOImpl();
        System.out.println(user);
        if(user!=null) {
            int uid=user.getUid();
            User user1=userDAO.getuserByUid(uid);
            req.setAttribute("user", user1);
            super.processTemplate("foreground/updateuser",req,resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
//            跳到登录页面
            resp.getWriter().print("<script language='javascript'>alert('用户未登录');</script>");
            resp.getWriter().println("<script>window.location='reg'</script>");
//            req.getRequestedSessionId()
        }

    }
}

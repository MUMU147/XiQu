package com.shumei.servlet.user_foregroundservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;
import com.shumei.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reg")
public class UserReg extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("foreground/login",request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取用户输入的用户名和密码
//        拿用户名进行查询，查看用户是否已注册
//        如果已经注册，alert，
//        如果没注册，以用户名和密码构造一个用户user类的对象
//        调用dao的相关方法完成用户添加
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UserDAO userDAO=new UserDAOImpl();
        User user=userDAO.selectUser(username,password);
        User user2=userDAO.findUserByEmail(username);
        Boolean flag=userDAO.findUserByUsername(username);
        if(flag){
//            提示该用户已注册
            if(user!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.getWriter().print("<script language='javascript'>alert('登陆成功');</script>");
                response.getWriter().println("<script>window.location='pshow'</script>");
            } else if (user2!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user2);
                response.getWriter().print("<script language='javascript'>alert('登陆成功');</script>");
                response.getWriter().println("<script>window.location='pshow'</script>");
            }
        }else{
//            可以注册
            User user1=new User();
            user1.setUsername(username);
//            加密
            user1.setPassword(MD5Util.getMd5(password));
            userDAO.addUser(user1);
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            response.getWriter().print("<script language='javascript'>alert('注册成功');</script>");
            response.getWriter().println("<script>window.location='pshow'</script>");
        }

    }
}

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
import java.sql.SQLException;

@WebServlet("/regemail")
public class UserRegByEmail extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("foreground/login",request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("foreground/login",request,response);
//        首先判断用户输入的验证码跟你给他发送的验证码是否一样
//        如果一样：（1）用户输入的这个邮箱号已经注册过 （查询，安邮箱号）直接登录就行，跳转到
//        （2）如果没注册过，提示注册成功，做跳转，跳转到前台首页
//        如果不一样：提示验证码错误
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String email=request.getParameter("email");
        String yzm=request.getParameter("yzm");
        String username=request.getParameter("username");
        HttpSession session=request.getSession();
        String code=(String) session.getAttribute("code");
        UserDAO userDAO=new UserDAOImpl();
        User user=userDAO.findUserByEmail(email);
        System.out.println(user);
        if(yzm.equals(code))
        {
            if(user==null){
//                需要注册
                User user1=new User();
                user1.setEmail(email);
                user1.setUsername(username);
                userDAO.addUser(user1);
                System.out.println(user1.getUid());
                user=user1;
                System.out.println(user1);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("<script language='javascript'>alert('注册成功！');</script>");
                response.getWriter().println(" <script>window.location='pshow'</script>");
//                user1没有id好
            }else
            if(user!=null){
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("<script language='javascript'>alert('该邮箱已注册，直接登录');</script>");
                response.getWriter().println("<script>window.location='pshow'</script>");
            }
//                跳转
            session.setAttribute("user",user);
            System.out.println(user);
        }else {
//            验证码输入错误
            System.out.println(111);
            response.getWriter().print("<script language='javascript'>alert('验证码输入错误');</script>");
            response.getWriter().println("<script>window.location='regemail'</script>");
        }
    }
}

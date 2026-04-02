package com.shumei.servlet.adminservlet;

import com.shumei.dao.AdminDAO;
import com.shumei.dao.impl.AdminDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminlogin")
public class AdminLogin extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      取cookie,并且填充到文本框和密码框里
        request.setCharacterEncoding("utf-8");
        Cookie[] cookies=request.getCookies();
        String username="";
        String password="";
        System.out.println(cookies.length);
        if(cookies!=null){
            for(Cookie cookie:cookies){
                System.out.println("cookiename"+cookie.getName());
                if(cookie.getName().equals("username")){
                    username= cookie.getValue();
                }
                if(cookie.getName().equals("password")){
                    password= cookie.getValue();
                }
            }}
//        变量放到属性域里
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        super.processTemplate("admin/login.html",request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String remember=request.getParameter("remember");
        System.out.println(remember+"记住我选中没");

        AdminDAO adminDAO=new AdminDAOImpl();
        Admin admin=adminDAO.getAdminUser(username,password);
        if(admin!=null){
//           判断复选框是否选中 如果被选中的话， 存 cookie
            if("1".equals(remember)){
                Cookie c_username=new Cookie("username",username);
                Cookie c_password=new Cookie("password",password);
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }
                System.out.println("登陆成功");
                HttpSession session=request.getSession();
                session.setAttribute("admin",admin);
                response.sendRedirect("pindex");
            }else{
                System.out.println("用户名或密码不对");
                super.processTemplate("admin/login.html",request,response);
        }
    }
}

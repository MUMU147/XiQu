package com.shumei.HTML;

import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/jifen")
public class jifen extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        System.out.println(user);
        if(user!=null) {
            super.processTemplate("foreground/jifen", req, resp);
        }else {
                resp.setContentType("text/html;charset=utf-8");
//            跳到登录页面
                resp.getWriter().print("<script language='javascript'>alert('用户未登录');</script>");
                resp.getWriter().println("<script>window.location='regemail'</script>");
//            req.getRequestedSessionId()
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

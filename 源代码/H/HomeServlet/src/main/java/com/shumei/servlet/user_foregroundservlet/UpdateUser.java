package com.shumei.servlet.user_foregroundservlet;

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

@WebServlet("/updateu")
public class UpdateUser extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        if (user != null) {
            UserDAO userDAO = new UserDAOImpl();
            int uid = Integer.parseInt(request.getParameter("uid"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String nickname = request.getParameter("nickname");
            // 开源安全说明：页面不回填密码，留空时沿用原密码，避免被清空
            if (password == null || password.trim().isEmpty()) {
                User oldUser = userDAO.selectByID(uid);
                if (oldUser != null) {
                    password = oldUser.getPassword();
                }
            }
            User u = new User(uid, username, password, email, phone, nickname);
            userDAO.updateUser(u);
            session.removeAttribute("user"); // 从会话中移除用户对象
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>alert('修改成功，请重新登陆');</script>");
            response.getWriter().println("<script>window.location='reg'</script>");
//        System.out.println(pname);
        }else {
            response.setContentType("text/html;charset=utf-8");
//            跳到登录页面
            response.getWriter().print("<script language='javascript'>alert('用户未登录');</script>");
            response.getWriter().println("<script>window.location='reg'</script>");
        }
    }
}

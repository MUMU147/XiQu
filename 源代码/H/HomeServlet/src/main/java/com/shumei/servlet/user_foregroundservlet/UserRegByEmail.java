package com.shumei.servlet.user_foregroundservlet;

import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;
import com.shumei.util.RandomUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/regemail")
public class UserRegByEmail extends ViewBaseServlet {
    //  对于邮箱：
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String USERNAME_PREFIX = "user_";
    private static final int TEMP_PASSWORD_LENGTH = 12;
    private boolean isEmail(String input) {
        // 使用您的邮箱正则表达式进行验证
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(input);
        return emailMatcher.matches();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("foreground/login",request,response);
    }
    //        首先判断用户输入的验证码跟你给他发送的验证码是否一样
//        如果一样：（1）用户输入的这个 邮箱号/手机号 已经注册过 （查询，安邮箱号）直接登录就行，跳转到
//        （2）如果没注册过，提示注册成功，做跳转，跳转到前台首页
//        如果不一样：提示验证码错误
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("foreground/login",request,response);
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String email = request.getParameter("email");   //获取号码
        HttpSession session=request.getSession();
        UserDAO userDAO=new UserDAOImpl();
        User user=userDAO.findUserByEmail(email);   //根据邮箱查找用户
        User user2=userDAO.findUserByPhone(email);  //根据手机号查找用户
        String yzm=request.getParameter("yzm"); //获取验证码
        String code=(String) session.getAttribute("code");
        if(yzm.equals(code))    //判断验证码是否正确
        {
            if(user==null && user2==null){  //没有注册过
//                需要注册
                User user1=new User();
                // 开源安全说明：禁止使用固定账号/弱口令，改为随机临时账号与口令
                user1.setUsername(generateTempUsername());    //用户名
                user1.setPassword(generateTempPassword());    //临时密码（不回显）
                if(isEmail(email)){ //邮箱注册
                    user1.setEmail(email);
                    user=user1; //注册成功
                    userDAO.addUser(user1); //注册成功
                    session.setAttribute("user",user1);
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().print("<script language='javascript'>alert('注册成功，请尽快在个人中心设置新密码。');</script>");
                    response.getWriter().println(" <script>window.location='reg'</script>");
//                user1没有id好
                }else{
                    user1.setPhone(email);  //手机号注册
                    userDAO.addUser(user1); //注册成功
                    session.setAttribute("user",user1);
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().print("<script language='javascript'>alert('注册成功，请尽快在个人中心设置新密码。');</script>");
                    response.getWriter().println(" <script>window.location='reg'</script>");
                }
            }else
            if(user!=null || user2!=null){  //注册过
                if(user!=null){
                    session.setAttribute("user",user);
                }else if(user2!=null){
                    session.setAttribute("user",user2);
                }
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("<script language='javascript'>alert('此号码已存在，登录成功');</script>");
                response.getWriter().println("<script>window.location='pshow'</script>");
            }
//                跳转
             //注册成功
        }else {
//            验证码输入错误
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>alert('验证码输入错误');</script>");
            response.getWriter().println("<script>window.location='regemail'</script>");
        }
    }

    private String generateTempUsername() {
        return USERNAME_PREFIX + System.currentTimeMillis();
    }

    private String generateTempPassword() {
        final String alphabet = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789@#";
        return RandomUtil.randomFromCharset(alphabet, TEMP_PASSWORD_LENGTH);
    }
}

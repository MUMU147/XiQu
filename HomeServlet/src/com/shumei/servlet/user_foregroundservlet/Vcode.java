package com.shumei.servlet.user_foregroundservlet;

import com.shumei.servlet.ViewBaseServlet;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet("/getvcode")
public class Vcode extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String to = "1029733710@qq.com";
        String to=request.getParameter("email");
        System.out.println("用户输入的email"+to);
        String code = generateRandomCode(); // 生成随机验证码
        String message = "您的验证码是：" + code;
        System.out.println(code);
        sendEmail(to, message);
        HttpSession session=request.getSession();
        session.setAttribute("code",code);
        System.out.println(code);
    }
    private void sendEmail(String to, String message) {
        // 设置邮箱服务器地址、端口、用户名和密码

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com"); // 修改为你的SMTP服务器地址
        props.put("mail.smtp.port", "587"); // 修改为你的SMTP服务器端口，通常为587或465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 如果你的SMTP服务器需要TLS加密，启用此选项

        // 创建一个认证对象，并设置SMTP服务器的认证信息（用户名和授权码）
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wlnhome", "ktjgajtlxfcmdhfh"); // 修改为你的SMTP认证用户名和密码
            }
        });

        // 创建一个邮件对象，设置发件人、收件人、主题和内容
        try {
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress("wlnhome@qq.com")); // 修改为你的发件人邮箱地址
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // 设置收件人邮箱地址
            message1.setSubject("注册验证码"); // 设置邮件主题
            message1.setText(message); // 设置邮件内容，包含验证码链接或说明等。这里直接使用生成好的验证码作为内容。
            Transport.send(message1); // 发送邮件，这里不再需要调用Transport.connect()方法，因为我们在创建Session对象时已经完成了认证。
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRandomCode() {
        // 生成一个6位数的随机验证码，可以根据你的需求修改长度。
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // 生成100000-999999之间的随机数
        return String.valueOf(code);
    }
}

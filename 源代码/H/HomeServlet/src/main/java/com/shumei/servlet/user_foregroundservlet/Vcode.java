package com.shumei.servlet.user_foregroundservlet;
// This file is auto-generated, don't edit it. Thanks.

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.tea.TeaException;
import com.shumei.servlet.ViewBaseServlet;
import com.shumei.util.RandomUtil;
import com.aliyun.teaopenapi.models.Config;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@WebServlet("/getVcode")
public class Vcode extends ViewBaseServlet {
     private  String code = generateRandomCode(); // 生成随机验证码
    // 开源安全说明：敏感配置请从环境变量读取，禁止硬编码到仓库
    private static final String SMTP_USERNAME_ENV = "SMTP_USERNAME";
    private static final String SMTP_APP_PASSWORD_ENV = "SMTP_APP_PASSWORD";
    private static final String SMTP_FROM_ENV = "SMTP_FROM";
    private static final String ALIYUN_ACCESS_KEY_ID_ENV = "ALIBABA_CLOUD_ACCESS_KEY_ID";
    private static final String ALIYUN_ACCESS_KEY_SECRET_ENV = "ALIBABA_CLOUD_ACCESS_KEY_SECRET";
    //    对于手机号（假设我们考虑中国的手机号格式，以1开头，11位数字）
    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
    //  对于邮箱：
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private boolean isPhoneNumber(String input) {   // 使用您的手机号正则表达式进行验证
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);
        Matcher phoneMatcher = phonePattern.matcher(input);
        return phoneMatcher.matches();
    }
    private boolean isEmail(String input) { // 使用您的邮箱正则表达式进行验证
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(input);
        return emailMatcher.matches();
    }
    //    手机验证码
//    private static void main(){
//        Config config = new Config()
//                // 必填，您的 AccessKey ID
//                .setAccessKeyId("<REDACTED_ACCESS_KEY_ID>")
//                // 必填，您的 AccessKey Secret
//                .setAccessKeySecret("<REDACTED_ACCESS_KEY_SECRET>");
//        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
//        config.endpoint = "dysmsapi.aliyuncs.com";
//        // 生成随机验证码
//        String code = generateRandomCode();
//        Client client = null;
//        try {
//            client = new Client(config);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        SendSmsRequest sendSmsRequest = new SendSmsRequest()
//                .setSignName("阿里云短信测试")//短信签名
//                .setTemplateCode("SMS_154950909")//短信模板
//                .setPhoneNumbers("157xxxxxxxx")//这里填写接受短信的手机号码
//                .setTemplateParam("{\"code\":\"" + code + "\"}");//验证码
//        // 复制代码运行请自行打印 API 的返回值
//        try {
//            client.sendSms(sendSmsRequest);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(sendSmsRequest);
//    }
    @Override
//    获取验证码
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String to = request.getParameter("email");
        String message = "您的验证码是：" + code;
        String[] args = new String[]{to, message};
        // 判断是否为手机号
        if (isPhoneNumber(to)) {    // 手机号
            try {
                main(args);
            } catch (Exception e) {
                System.err.println("发送验证码失败：" + e.getMessage());
                e.printStackTrace();
            }
            response.getWriter().print("<script language='javascript'>alert('验证码已发送，请注意查收！');</script>");
        } else if (isEmail(to)) {
            System.out.println("邮箱地址： " + to);
            sendEmail(to, message);
            response.getWriter().print("<script language='javascript'>alert('验证码已发送至您的邮箱，请注意查收！');</script>");
        } else {
            response.getWriter().print("<script language='javascript'>alert('请输入有效的手机号或邮箱！');</script>");
        }
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
    }
    @Override
//    验证码提交
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req,resp);
    }
    //    邮箱发验证码
    private void sendEmail(String to, String message) {
        // 设置邮箱服务器地址、端口、用户名和密码
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com"); // 修改为你的SMTP服务器地址
        props.put("mail.smtp.port", "587"); // 修改为你的SMTP服务器端口，通常为587或465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 如果你的SMTP服务器需要TLS加密，启用此选项
        final String smtpUsername = requireEnv(SMTP_USERNAME_ENV);
        final String smtpPassword = requireEnv(SMTP_APP_PASSWORD_ENV);
        final String smtpFrom = getEnvOrDefault(SMTP_FROM_ENV, smtpUsername);
        // 创建一个认证对象，并设置SMTP服务器的认证信息（用户名和授权码）
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 开源安全说明：SMTP 账号和授权码由环境变量注入
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });
        // 创建一个邮件对象，设置发件人、收件人、主题和内容
        try {
            Message message1 = new MimeMessage(session);
            // 开源安全说明：发件人地址从环境变量读取，默认回落为 SMTP 用户名
            message1.setFrom(new InternetAddress(smtpFrom));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // 设置收件人邮箱地址
            message1.setSubject("注册验证码"); // 设置邮件主题
            message1.setText(message); // 设置邮件内容，包含验证码链接或说明等。这里直接使用生成好的验证码作为内容。
            Transport.send(message1); // 发送邮件，这里不再需要调用Transport.connect()方法，因为我们在创建Session对象时已经完成了认证。
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    // 生成一个6位数的随机验证码
    private  String generateRandomCode() {
        // 生成一个6位数验证码（统一使用随机工具类）
        return RandomUtil.randomDigits(6);
    }
//    获取环境变量的值，如果环境变量不存在，则返回默认值
    private static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
    // 发送手机验证码的方法
    // 在这里实现发送短信的逻辑，包括调用阿里云短信服务 API 等
    // 你需要填充 AccessKey ID、AccessKey Secret、Endpoint 等信息，并构造 SendSmsRequest 对象
    // 然后使用 Client 对象调用 sendSms 方法发送短信
    // 注意：出于安全考虑，不要在代码中硬编码 AccessKey ID 和 AccessKey Secret，应该使用更安全的方式来管理这些敏感信息
    // 请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID 和 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
    // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取 AccessKey 的方式进行调用，仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
    public void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        String phoneNumber = args.get(0);
        // 请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID 和 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取 AccessKey 的方式进行调用，仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.dysmsapi20170525.Client client = Vcode.createClient(
                requireEnv(ALIYUN_ACCESS_KEY_ID_ENV),
                requireEnv(ALIYUN_ACCESS_KEY_SECRET_ENV)
        );
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                // 开源安全说明：短信目标号码取自请求参数，不在代码中写死
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam("{\"code\":\""+code+"\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }

    private static String requireEnv(String envName) {
        String value = System.getenv(envName);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Missing required environment variable: " + envName);
        }
        return value.trim();
    }

    private static String getEnvOrDefault(String envName, String defaultValue) {
        String value = System.getenv(envName);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }
    // main 方法作为测试入口点
//    public static void main(String[] args)  {
//        // 这里可以测试 sendSms 方法，例如：
//        String code = generateRandomCode(); // 生成随机验证码
//        String message = "您的验证码是：" + code;
//        String[] args_ = new String[] {code, message};
//        try {
//            Vcode.sendPhone(args_);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("验证码是：" + code);
//    }
}

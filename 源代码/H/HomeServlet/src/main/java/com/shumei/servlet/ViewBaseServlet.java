package com.shumei.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewBaseServlet extends HttpServlet {

    private TemplateEngine templateEngine;
    // 记录解析所需的后缀，供 processTemplate 做模板名去重（避免 login.html.html）
    private String viewSuffix;

    @Override
    public void init() throws ServletException {

        // 1.获取ServletContext对象
        ServletContext servletContext = this.getServletContext();
        System.out.println(servletContext);
        // 2.创建Thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        System.out.println(templateResolver);
        // 3.给解析器对象设置参数
        // ①HTML是默认模式，明确设置是为了代码更容易理解
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // ②设置前缀
        String viewPrefix = servletContext.getInitParameter("view-prefix");
        System.out.println(viewPrefix);
        // web.xml 可能未被打包/加载成功时，initParameter 可能为 null。
        // 兜底：使用约定的模板目录前缀，避免找不到资源。
        if (viewPrefix == null || viewPrefix.trim().isEmpty()) {
            viewPrefix = "/view/";
        }
        if (!viewPrefix.startsWith("/")) {
            viewPrefix = "/" + viewPrefix;
        }
        if (!viewPrefix.endsWith("/")) {
            viewPrefix = viewPrefix + "/";
        }
        templateResolver.setPrefix(viewPrefix);

        // ③设置后缀
        String viewSuffix = servletContext.getInitParameter("view-suffix");
        System.out.println(viewSuffix);
        if (viewSuffix == null || viewSuffix.trim().isEmpty()) {
            viewSuffix = ".html";
        }
        this.viewSuffix = viewSuffix;
        templateResolver.setSuffix(viewSuffix);

        // ④设置缓存过期时间（毫秒）
        templateResolver.setCacheTTLMs(60000L);

        // ⑤设置是否缓存
        templateResolver.setCacheable(true);

        // ⑥设置服务器端编码方式
        templateResolver.setCharacterEncoding("utf-8");

        // 4.创建模板引擎对象
        templateEngine = new TemplateEngine();

        // 5.给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);
        // 在 init() 方法中添加
        System.out.println("view-prefix: " + viewPrefix);
        System.out.println("view-suffix: " + viewSuffix);

    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        System.out.println("templateName: " + templateName);
        if (templateName == null) {
            throw new IllegalArgumentException("templateName must not be null");
        }
        // ServletContextTemplateResolver 会再拼接 prefix/suffix。
        // 这里统一把 templateName 处理成：不以 "/" 开头，且不带重复的扩展名。
        if (templateName.startsWith("/")) {
            templateName = templateName.substring(1);
        }
        if (viewSuffix != null && templateName.endsWith(viewSuffix)) {
            templateName = templateName.substring(0, templateName.length() - viewSuffix.length());
        }
        // 1.设置响应体内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");
        // 2.创建WebContext对象
        WebContext webContext = new WebContext(req, resp, getServletContext());
        // 3.处理模板数据
        templateEngine.process(templateName, webContext, resp.getWriter());

    }
}
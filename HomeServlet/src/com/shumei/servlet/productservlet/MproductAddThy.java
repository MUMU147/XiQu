package com.shumei.servlet.productservlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.pojo.Product;
import com.shumei.servlet.ViewBaseServlet;
import com.shumei.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/mproductadd")
@MultipartConfig
public class MproductAddThy extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
//        super.processTemplate("add",request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
//        获取表单数据
            String pname = request.getParameter("pname");
            String price = request.getParameter("price");
            String stock = request.getParameter("stock");
            if (Util.isEmpty(pname) || Util.isEmpty(price) || Util.isEmpty(stock)) {
                throw new RuntimeException("输入非法！！！");
            }
            Part part = request.getPart("img");
            String filename = UUID.randomUUID() + part.getName();
            String filepath = request.getServletContext().getRealPath("/") + "view/admin/images/";
            part.write(filepath + filename);
            String saveUrl = "view/admin/images/" + filename;
//创建对象
            Product product = new Product(pname, Double.parseDouble(price), Integer.parseInt(stock), saveUrl);
            ProductDAO productDAO = new ProductDAOImpl();
            productDAO.addProduct(product);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>alert('添加成功');</script>");
            response.getWriter().println("<script>window.location='pindex'</script>");
        }
        else {
            super.processTemplate("admin/login",request,response);
        }
    }
}
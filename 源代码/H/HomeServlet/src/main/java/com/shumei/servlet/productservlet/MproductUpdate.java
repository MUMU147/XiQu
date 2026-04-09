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
import java.io.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.UUID;

@MultipartConfig
@WebServlet("/proupdate")
public class MproductUpdate extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null){
            ProductDAO productDAO=new ProductDAOImpl();
            System.out.println(111);
            int pid=Integer.parseInt(request.getParameter("pid"));
            String pname = request.getParameter("pname");
            String price = request.getParameter("price");
            String stock=request.getParameter("stock");
            String oldimg=request.getParameter("oldimg");
            Part part=request.getPart("oldimg");
            Part newimg=request.getPart("newimg");
            String img;
            System.out.println(pid);
            if(newimg==null){
                Product product=productDAO.getProductByPid(pid);
                // 假设getImg()方法返回图片文件的路径
                String imgPath = product.getImg();
                System.out.println(imgPath);
                // 你可以使用这个路径来构建图片的完整URL或路径，并在需要时显示它。
                // 例如，在HTML中显示图片：
                img = request.getContextPath() + imgPath;
                System.out.println(img);
            }
            else{
                String filename = UUID.randomUUID() + newimg.getName();
                String filepath = request.getServletContext().getRealPath("/") + "view/admin/images/";
                newimg.write(filepath + filename);
                img = "view/admin/images/" + filename;
//                System.out.println(img);
            }
//            System.out.println(pid);
            Product p = new Product(pid,pname, Double.parseDouble(price), Integer.parseInt(stock), img);
            System.out.println(p);
            productDAO.updateProduct(p);
//            System.out.println(p);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>alert('修改成功');</script>");
            response.getWriter().println("<script>window.location='pindex'</script>");
        }
        else {
            super.processTemplate("admin/login", request, response);
        }
    }
}

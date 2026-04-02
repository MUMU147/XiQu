package com.shumei.servlet.productservlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Product;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/proupdate")
public class MproductUpdate extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String pname=request.getParameter("pname");
        Double price=Double.parseDouble(request.getParameter("price"));
        int stock= Integer.parseInt(request.getParameter("stock"));
        int pid= Integer.parseInt(request.getParameter("pid"));
        Product p=new Product(pid,pname,price,stock,"");
        ProductDAO productDAO=new ProductDAOImpl();
        productDAO.updateProduct(p);
        response.sendRedirect("pindex");
//        System.out.println(pname);
    }
}

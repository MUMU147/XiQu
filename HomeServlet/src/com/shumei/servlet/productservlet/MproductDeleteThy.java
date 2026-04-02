package com.shumei.servlet.productservlet;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mproductdel")
public class MproductDeleteThy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO=new ProductDAOImpl();
        int pid= Integer.parseInt(request.getParameter("pid"));
        productDAO.delproduct(pid);
        response.sendRedirect("pindex");
    }
}
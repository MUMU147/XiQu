package com.shumei.servlet.product_fore;

import com.shumei.dao.ProductDAO;

import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Product;

import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pshow")
public class ProductShow extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> Products = productDAO.getproductList();
            request.setAttribute("sproduct", Products);
//            System.out.println(Products);
            super.processTemplate("foreground/showproduct", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

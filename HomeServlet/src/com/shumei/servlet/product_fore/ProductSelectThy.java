package com.shumei.servlet.product_fore;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Product;
import com.shumei.servlet.ViewBaseServlet;
import com.shumei.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/psindex")
public class ProductSelectThy extends ViewBaseServlet {
private  ProductDAO productDAO=new ProductDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
//        获取表单数据
        String keyword = "";
        String buttonValue = request.getParameter("search");
        if ("查询".equals(buttonValue)) {
            if (!(Util.isEmpty(request.getParameter("keyword")))) {
                keyword = request.getParameter("keyword");
            }
        } //创建对象
        List<Product> list = productDAO.getproductList(keyword);
        System.out.println(list.size());
//        response.sendRedirect("pindex");
        request.setAttribute("sproduct", list);
        super.processTemplate("foreground/showproduct", request, response);
    }
}
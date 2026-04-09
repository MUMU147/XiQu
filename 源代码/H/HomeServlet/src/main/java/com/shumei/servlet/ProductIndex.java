package com.shumei.servlet;

import com.shumei.dao.OrderDAO;
import com.shumei.dao.ProductDAO;
import com.shumei.dao.TicketDAO;
import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.OrderDAOImpl;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.dao.impl.TicketDAOImpl;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pindex")
public class ProductIndex extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Admin admin= (Admin) session.getAttribute("admin");
        if(admin!=null) {
            ProductDAO productDAO = new ProductDAOImpl();
            UserDAO userDAO = new UserDAOImpl();
            OrderDAO orderDAO = new OrderDAOImpl();
            TicketDAO ticketDAO=new TicketDAOImpl();
            List<Product> Products = productDAO.getproductList();
            List<User> users = userDAO.getUsersList();
            List<Order> orders = orderDAO.getOrderList();
            List<Ticket> tickets=ticketDAO.getticketList();
            request.setAttribute("products", Products);
            request.setAttribute("users", users);
            request.setAttribute("orders", orders);
            request.setAttribute("tickets",tickets);
//            System.out.println(tickets);
            super.processTemplate("admin/index", request, response);
        } else {
            super.processTemplate("admin/login",request,response);
        }
    }
}


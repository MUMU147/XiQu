package com.shumei.servlet.ticketservlet;

import com.shumei.dao.TicketDAO;
import com.shumei.dao.impl.TicketDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.pojo.Ticket;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/tedit")
public class TicketEdit extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ProductDAO productDAO=new ProductDAOImpl();
//        int pid= Integer.parseInt(request.getParameter("pid"));
//        Product product=productDAO.getProductByPid(pid);
//        request.setAttribute("product",product);
//        super.processTemplate("proedit",request,response);
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            TicketDAO ticketDAO = new TicketDAOImpl();
            int ticID = Integer.parseInt(request.getParameter("ticId"));
            Ticket ticket = ticketDAO.getTicketByTid(ticID);
            request.setAttribute("ticket", ticket);
            super.processTemplate("admin/tedit", request, response);
        }else {
            super.processTemplate("admin/login",request,response);
        }
    }
}

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

@WebServlet("/tdetail")
public class Ticketdetail extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            TicketDAO ticketDAO = new TicketDAOImpl();
            int ticID = Integer.parseInt(request.getParameter("ticID"));
            System.out.println(ticID);
            Ticket ticket = ticketDAO.getTicketByTid(ticID);
            System.out.println(ticket);
            request.setAttribute("ticket", ticket);
            super.processTemplate("admin/ticketdetail", request, response);
        }else {
            super.processTemplate("admin/login",request,response);
        }
    }
}

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
import java.util.List;

@WebServlet("/tindex")
public class TicketIndex extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Admin admin= (Admin) session.getAttribute("admin");
        if(admin!=null) {
            TicketDAO ticketDAO=new TicketDAOImpl();
            List<Ticket> tickets=ticketDAO.getticketList();
            request.setAttribute("tickets",tickets);
            super.processTemplate("admin/tickets", request, response);
        } else {
            super.processTemplate("admin/login",request,response);
        }
    }
}


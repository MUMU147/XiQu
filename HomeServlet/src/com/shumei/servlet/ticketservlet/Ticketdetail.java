package com.shumei.servlet.ticketservlet;

import com.shumei.dao.TicketDAO;
import com.shumei.dao.impl.TicketDAOImpl;
import com.shumei.pojo.Ticket;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tdetail")
public class Ticketdetail extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TicketDAO ticketDAO=new TicketDAOImpl();
        int ticID = Integer.parseInt(request.getParameter("ticId"));
        System.out.println(ticID);
        Ticket ticket=ticketDAO.getTicketByTid(ticID);
        request.setAttribute("ticket",ticket);
        super.processTemplate("ticketdetail",request,response);
    }
}

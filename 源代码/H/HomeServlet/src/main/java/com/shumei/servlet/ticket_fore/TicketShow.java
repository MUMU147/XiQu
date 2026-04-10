package com.shumei.servlet.ticket_fore;

import com.shumei.dao.ProductDAO;
import com.shumei.dao.TicketDAO;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.dao.impl.TicketDAOImpl;
import com.shumei.pojo.Product;
import com.shumei.pojo.Ticket;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tshow")
public class TicketShow extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TicketDAO ticketDAO=new TicketDAOImpl();
        List<Ticket> Tickets=ticketDAO.getticketList();
        request.setAttribute("sticket",Tickets);
//        System.out.println(Tickets);
        super.processTemplate("foreground/showticket", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

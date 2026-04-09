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

@WebServlet("/tupdate")
public class TicketUpdate extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            int ticID = Integer.parseInt(request.getParameter("ticId"));
            String tname = request.getParameter("tname");
            Double tprice = Double.parseDouble(request.getParameter("tprice"));
            String tkind = request.getParameter("tkind");
            Ticket t = new Ticket(ticID, tname, tprice, tkind, "");
            TicketDAO ticketDAO = new TicketDAOImpl();
            ticketDAO.updateTicket(t);
            System.out.println(t);
            response.sendRedirect("tindex");
//        System.out.println(pname);
        }else {
            super.processTemplate("admin/login",request,response);
        }
    }
}

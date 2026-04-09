package com.shumei.servlet.ticketservlet;

import com.shumei.dao.TicketDAO;
import com.shumei.dao.impl.TicketDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.pojo.Product;
import com.shumei.pojo.Ticket;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mytindex")
public class TicketSelectThy extends ViewBaseServlet {
private TicketDAO ticketDAO=new TicketDAOImpl();
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
            String searchone = request.getParameter("searchone");
            String searchtwo = request.getParameter("searchtwo");
            if ("查询".equals(searchone)) {
                //        获取表单数据
                String keyword = "";
                keyword = request.getParameter("keyword");
//创建对象
                List<Ticket> list = ticketDAO.getTicketList(keyword);
                request.setAttribute("tickets", list);
                super.processTemplate("tickets", request, response);
            } else if ("显示所有".equals(searchtwo)) {
                //        获取表单数据
                String keyword = "";
                keyword = request.getParameter("keyword");
//创建对象
                List<Ticket> list = ticketDAO.getTicketList(keyword);
                request.setAttribute("tickets", list);
                super.processTemplate("admin/tickets", request, response);
            } else {
                super.processTemplate("admin/tadd", request, response);
            }
        }else {
            super.processTemplate("admin/login",request,response);
        }
    }
}
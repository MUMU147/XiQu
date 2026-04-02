package com.shumei.servlet.ticketservlet;

import com.shumei.dao.TicketDAO;
import com.shumei.dao.UserDAO;
import com.shumei.dao.impl.TicketDAOImpl;
import com.shumei.dao.impl.UserDAOImpl;
import com.shumei.pojo.Admin;
import com.shumei.pojo.Ticket;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;
import com.shumei.util.Util;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/mticketadd")
@MultipartConfig
public class TicketAddThy extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request,response);
        super.processTemplate("tadd",request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
//        获取表单数据
            String tname = request.getParameter("tname");
            Double tprice = Double.valueOf(request.getParameter("tprice"));
            String tkind = request.getParameter("tkind");
            if (Util.isEmpty(tname) || Util.isEmpty(String.valueOf(tprice))) {
                throw new RuntimeException("输入非法！！！");
            }
            Part part = request.getPart("timg");
            String filename = UUID.randomUUID() + part.getName();
            String filepath = request.getServletContext().getRealPath("/") + "view/images/";
            part.write(filepath + filename);
            String saveUrl = "view/images/" + filename;
//创建对象
            Ticket ticket = new Ticket(tname, tprice,tkind,saveUrl);
            TicketDAO ticketDAO = new TicketDAOImpl();
            ticketDAO.addTicket(ticket);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>alert('添加成功');</script>");
            response.getWriter().println("<script>window.location='pindex'</script>");
        }
        else {
            super.processTemplate("login",request,response);
        }
    }
}
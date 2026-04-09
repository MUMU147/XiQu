package com.shumei.servlet.collectionservlet;

import com.shumei.dao.CollectionDAO;
import com.shumei.dao.impl.CollectionDAOImpl;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delcollection")
public class CollectionDel extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取产品、获取用户session
//        调用DAO去完成删除 CollectionDAO的相关方法完成删除
//        方法：Boolean delCollection(int pid，int uid)
//        跳回mycollect
        request.setCharacterEncoding("utf-8");
        int pid= Integer.parseInt(request.getParameter("pid"));
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        CollectionDAO collectionDAO=new CollectionDAOImpl();
        collectionDAO.delCollection(pid,user.getUid());
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("<script language='javascript'>alert('取消成功');</script>");
        response.getWriter().println("<script>window.location='mycollect'</script>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

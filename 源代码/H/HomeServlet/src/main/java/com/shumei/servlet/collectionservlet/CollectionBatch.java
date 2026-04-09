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

@WebServlet("/batchCancle")
public class CollectionBatch extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        String ids[] =request.getParameterValues("ids");
        CollectionDAO collectionDAO=new CollectionDAOImpl();
        for(String pid:ids){
            collectionDAO.delCollection(Integer.parseInt(pid),user.getUid());
        }
//        request.getRequestDispatcher("/mycollect").forward(request,response);
//        super.processTemplate("foreground/mycollection",request,response);
    }
}

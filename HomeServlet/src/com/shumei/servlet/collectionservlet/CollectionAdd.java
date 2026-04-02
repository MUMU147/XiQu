package com.shumei.servlet.collectionservlet;

import com.shumei.dao.CollectionDAO;
import com.shumei.dao.ProductDAO;
import com.shumei.dao.impl.CollectionDAOImpl;
import com.shumei.dao.impl.ProductDAOImpl;
import com.shumei.pojo.Collection;
import com.shumei.pojo.Product;
import com.shumei.pojo.User;
import com.shumei.servlet.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addcollection")
public class CollectionAdd extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        System.out.println(user);
        if(user!=null) {
                int pid = Integer.parseInt(req.getParameter("pid"));
                ProductDAO productDAO = new ProductDAOImpl();
                Product product = productDAO.getProductByPid(pid);
                Collection collection = new Collection();
                collection.setProduct(product);
                collection.setUser(user);
                CollectionDAO collectionDAO = new CollectionDAOImpl();
                boolean flag = collectionDAO.findUserByCollectionPid(product.getPid(), user.getUid());
                if (flag) {
                    resp.getWriter().print("<script language='javascript'>alert('该产品已加入收藏');</script>");
                    resp.getWriter().println("<script>window.location='pshow'</script>");
                } else {
                    collectionDAO.addCollection(collection);
                    resp.getWriter().print("<script language='javascript'>alert('收藏成功');</script>");
                    resp.getWriter().println("<script>window.location='pshow'</script>");
                }
            } else {
//            跳到登录页面
            resp.getWriter().print("<script language='javascript'>alert('用户未登录');</script>");
            resp.getWriter().println("<script>window.location='regemail'</script>");
//            req.getRequestedSessionId()
            }
        }
    }

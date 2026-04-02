package com.shumei.dao.impl;
//方法必须是public，属性private
import com.shumei.dao.CollectionDAO;
import com.shumei.dao.ProductDAO;
import com.shumei.pojo.Collection;
import com.shumei.pojo.User;
import com.shumei.pojo.Product;
import com.shumei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectionDAOImpl implements CollectionDAO {
    public void addCollection(Collection collection){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn= DBUtil.getConnection();
//            事务
            conn.setAutoCommit(false);
            String sql="insert into collection(pid,uid) values(?,?)";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,collection.getProduct().getPid());
            ps.setInt(2,collection.getUser().getUid());
            int count=ps.executeUpdate();
            System.out.println("加入了收藏的条数"+count);
//            提交，执行完提交才会更新到数据库
            conn.commit();
            conn.close();
            DBUtil.close(conn,ps,rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> myCollection(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList=new ArrayList<>();
        try {
            conn= DBUtil.getConnection();
            conn.setAutoCommit(false);
            String sql="select * from collection where uid=? order by ctime";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,user.getUid());
            rs=ps.executeQuery();
            ProductDAO productDAO = new ProductDAOImpl();

//            指针下移
            while (rs.next()){
                int pid= rs.getInt(1);
                Product product = productDAO.getProductByPid(pid);
                productList.add(product);
            }
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public Boolean delCollection(int pid,int uid){
        Boolean flag=false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn= DBUtil.getConnection();
            conn.setAutoCommit(false);
            String sql="delete from collection where pid=? and uid=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,pid);
            ps.setInt(2,uid);
            int count=ps.executeUpdate();
            System.out.println(count);

            conn.commit();
            flag=true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return  flag;
    }
@Override
    public Boolean findUserByCollectionPid(int pid,int uid) {
        Product p=new Product();
        User u = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean flag=false;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from collection where pid=? uid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.setInt(2,uid);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag=true;
            } else {
                flag=false;
            }
            DBUtil.close(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}

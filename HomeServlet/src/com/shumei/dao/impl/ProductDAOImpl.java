package com.shumei.dao.impl;

import com.shumei.dao.ProductDAO;
import com.shumei.pojo.Product;
import com.shumei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
//    @Test
//    public void test() throws SQLException {
//        List<Product> list = getproductList();
//        System.out.println(list.size());
////          delproduct(50);
//    }

    @Override
    public List<Product> getproductList() {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from product";
            ps = conn.prepareStatement(sql);
            re = ps.executeQuery();
            while (re.next()) {
                int pid = re.getInt("pid");
                String pname = re.getString("pname");
                Double price = re.getDouble("price");
                int stock = re.getInt("stock");
                String img=re.getString("img");
//               System.out.println("pid: "+pid+"pname: "+pname+"price: "+price+"stock: "+stock);
                Product product = new Product();
                product.setPid(re.getInt("pid"));
                product.setPname(re.getString("pname"));
                product.setPrice(re.getDouble("price"));
                product.setStock(re.getInt("stock"));
                product.setImg(re.getString("img"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return list;
    }
    @Override
    public boolean delproduct(int pid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from product where pid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);
            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return false;
    }
    public void updateProduct(Product product) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        //出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            //事务的try
            try {
                conn.setAutoCommit(false);
                String sql = "update product set pname=?,price=?,stock=?,img=? where pid=? ";
                //获取数据库预操作对象
                ps = conn.prepareStatement(sql);
                ps.setString(1, product.getPname());
                ps.setDouble(2, product.getPrice());
                ps.setInt(3, product.getStock());
                ps.setString(4, product.getImg());
                ps.setInt(5,product.getPid());
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException se) {
                System.out.println(se.getMessage());
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
    }
    public void addProduct(Product product) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            try {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO product(pname,price,stock,Img) VALUE(?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, product.getPname());
                ps.setDouble(2, product.getPrice());
                ps.setInt(3, product.getStock());
                ps.setString(4, product.getImg());
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException se) {
                System.out.println(se.getMessage());
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
    }
//    @Test
//    public void test(){
//        product product=getProductByPid(42);
//        System.out.println(product.getPname());
//        System.out.println(getproductList("面塑").size());
//    }
    @Override
    public Product getProductByPid(Integer pid) {
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        Product product = null;
        try {
            conn = DBUtil.getConnection();

            String sql = "SELECT pid,pname,price,stock,img FROM product WHERE pid=?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            if (rs.next()){
                String pname = rs.getString("pname");
                double price = rs.getInt("price");
                int stock = rs.getInt("stock");
                String img = rs.getString("img");
                product = new Product(pid, pname, price, stock,img);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return product;
    }

    @Override
    public List<Product> getproductList(String keyword) {
        List<Product> list = new ArrayList<>();
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT pid,pname,price,stock,img FROM product WHERE pname like ?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                int pid=rs.getInt("pid");
                String pname = rs.getString("pname");
                double price = rs.getInt("price");
                int stock = rs.getInt("stock");
                String img = rs.getString("img");
                list.add(new Product(pid,pname,price,stock,img));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

}

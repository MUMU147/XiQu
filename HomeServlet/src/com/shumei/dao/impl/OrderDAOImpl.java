package com.shumei.dao.impl;

import com.shumei.dao.OrderDAO;
import com.shumei.pojo.Order;
import com.shumei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{
//        @Test
//    public void test() throws SQLException {
//        OrderDAO orderDAO=new OrderDAOImpl();
//        List<Order> orderList = orderDAO.getOrderList();
//        System.out.println(orderList.size());
//    }
    @Override
    public List<Order> getOrderList() {
        List<Order> List =new ArrayList<>();
//        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
//            System.out.println(111);
//            String sql = "select * from product";
            String sql = "select * from `order`";
//            System.out.println(222);
            ps = conn.prepareStatement(sql);
            re = ps.executeQuery();
            while (re.next()) {
//                int pid = re.getInt("pid");
//                String pname = re.getString("pname");
//                Double price = re.getDouble("price");
//                int stock = re.getInt("stock");
                int oid=re.getInt("oid");
                String otime=re.getString("otime");
                int ophone=re.getInt("ophone");
                String own=re.getString("own");
                String oaddress=re.getString("oaddress");
                Double omoney=re.getDouble("omoney");
//                System.out.println("uid: "+oid+'\t'+"uname: "+otime+'\t'+"password: "+ophone+'\t'+"email: "+own+'\t'+"nickname"+oaddress+omoney);
//                Product product = new Product();
//                product.setPid(re.getInt("pid"));
//                product.setPname(re.getString("pname"));
//                product.setPrice(re.getDouble("price"));
//                product.setStock(re.getInt("stock"));
//                System.out.println(333);
                Order order=new Order();
                order.setOid(re.getInt("oid"));
                order.setOtime(re.getString("otime"));
                order.setOphone(re.getInt("ophone"));
                order.setOwn(re.getString("own"));
                order.setOaddress(re.getString("oaddress"));
                order.setOmoney(re.getDouble("omoney"));
                List.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return List;
    }

    @Override
    public boolean dorder(int oid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from order where oid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return false;
    }


    @Override
    public void updateOrder(Order order) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        //出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            //事务的try
            try {
                conn.setAutoCommit(false);
                String sql = "update order set otime=?,ophone=?,own=?,oaddress=?,omoney=? where oid=? ";
                //获取数据库预操作对象
                ps = conn.prepareStatement(sql);
                ps.setString(1, order.getOtime());
                ps.setInt(2, order.getOphone());
                ps.setString(3, order.getOwn());
                ps.setString(4, order.getOaddress());
                ps.setDouble(5,order.getOmoney());
                ps.setInt(6, order.getOid());
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

    @Override
    public void addOrder(Order order) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            try {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO order(oid,otime,ophone,own,oaddress,omoney) VALUE(?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, order.getOtime());
                ps.setInt(2, order.getOphone());
                ps.setString(3, order.getOwn());
                ps.setString(4, order.getOaddress());
                ps.setDouble(5,order.getOmoney());
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

    public Order getOrderByPid(Integer oid) {
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        Order order = null;
        try {
            conn = DBUtil.getConnection();

            String sql = "SELECT oid,otime,ophone,own,oaddress,omoney FROM order WHERE oid=?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            rs = ps.executeQuery();
            if (rs.next()){
                String otime=  rs.getString("otime");
                int ophone=rs.getInt("ophone");
                String own =  rs.getString("own");
                String oaddress=rs.getString("oaddress");
                Double omoney=rs.getDouble("omoney");
                order = new Order(oid,otime,ophone,own,oaddress,omoney);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return order;
    }

    public List<Order> getOrderList(String keyword) {
        List<Order> orderlist = new ArrayList<>();
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT oid,otime,ophone,own,oaddress,omoney FROM order WHERE omoney like ?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                int oid=rs.getInt("oid");
                String otime=  rs.getString("otime");
                int ophone=rs.getInt("ophone");
                String own =  rs.getString("own");
                String oaddress=rs.getString("oaddress");
                Double omoney=rs.getDouble("omoney");
                orderlist.add(new Order(oid,otime,ophone,own,oaddress,omoney));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return orderlist;
    }
}

package com.shumei.dao.impl;

import com.shumei.pojo.Ticket;
import com.shumei.util.DBUtil;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements com.shumei.dao.TicketDAO {
    @Override
    public List<Ticket> getticketList() {
        List<Ticket> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from tickets";
            ps = conn.prepareStatement(sql);
            re = ps.executeQuery();
            while (re.next()) {
                int ticID = re.getInt("ticID");
                String tname = re.getString("tname");
                Double tprice = re.getDouble("tprice");
                String timg=re.getString("timg");
                String tkind=re.getString("tkind");
                int tcount=re.getInt("tcount");
//               System.out.println("pid: "+pid+"pname: "+pname+"price: "+price+"stock: "+stock);
                Ticket ticket = new Ticket();
                ticket.setTicID(re.getInt("ticID"));
                ticket.setTname(re.getString("tname"));
                ticket.setTprice(re.getDouble("tprice"));
                ticket.setTcount(re.getInt("tcount"));
                ticket.setTimg(re.getString("timg"));
                ticket.setTkind(re.getString("tkind"));
                list.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return list;
    }

    @Override
    public boolean delticket(int ticID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from tickets where ticID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ticID);
            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return false;
    }

    @Override
    public void updateTicket(Ticket ticket) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        //出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            //事务的try
            try {
                conn.setAutoCommit(false);
                String sql = "update tickets set tname=?,tprice=?,tkind=?,timg=?,tcount=? where ticID =? ";
                //获取数据库预操作对象
                ps = conn.prepareStatement(sql);
                ps.setString(1, ticket.getTname());
                ps.setDouble(2, ticket.getTprice());
                ps.setString(3, ticket.getTkind());
                ps.setString(4, ticket.getTimg());
                ps.setInt(6,ticket.getTcount());
                ps.setInt(5,ticket.getTicID());
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
    public void addTicket(Ticket ticket) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            try {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO tickets(tname,tprice,tkind,timg,tcount) VALUE(?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, ticket.getTname());
                ps.setDouble(2, ticket.getTprice());
                ps.setString(3, ticket.getTkind());
                ps.setString(4, ticket.getTimg());
                ps.setInt(5,ticket.getTcount());
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
@Test
//public void test(){
//        Ticket ticket=getTicketByTid(6);
//        System.out.println(ticket.getTname());
//    }

    @Override
    public Ticket getTicketByTid(Integer ticID) {
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        Ticket ticket = null;
        try {
            conn = DBUtil.getConnection();

            String sql = "SELECT ticID,tname,tprice,tkind,timg,tcount FROM tickets WHERE ticID=?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ticID);
            rs = ps.executeQuery();
            if (rs.next()){
                String tname = rs.getString("tname");
                Double tprice = rs.getDouble("tprice");
                String tkind=rs.getString("tkind");
                String timg=rs.getString("timg");
                int tcount=rs.getInt("tcount");
                ticket = new Ticket(ticID,tname,tprice,tkind,timg,tcount);
                System.out.println(ticket);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return ticket;
    }

    @Override
    public List<Ticket> getTicketList(String keyword) {
        List<Ticket> list = new ArrayList<>();
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT ticID,tname,tprice,tcount,timg FROM tickets WHERE tkind like ?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");    // %是通配符，表示任意字符
            rs = ps.executeQuery();
            while (rs.next()){
                int ticID=rs.getInt("ticID");
                String tname = rs.getString("tname");
                Double tprice = rs.getDouble("tprice");
                String tcount=rs.getString("tcount");
                String timg=rs.getString("timg");
                list.add(new Ticket(ticID,tname,tprice,tcount,timg));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        System.out.println(list);
        return list;    //返回结果
    }
}

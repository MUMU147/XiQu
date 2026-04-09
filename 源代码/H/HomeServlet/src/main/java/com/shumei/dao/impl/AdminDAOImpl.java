package com.shumei.dao.impl;

import com.shumei.dao.AdminDAO;
import com.shumei.pojo.Admin;
import com.shumei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    public Admin getAdminUser(String username, String password) {
        Admin admin = new Admin();
        String sql = "select * from admin where username=? and password=?";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin.setAdminID(rs.getInt("adminID"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
            } else {
                admin = null;
            }
            DBUtil.close(conn,ps,rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}

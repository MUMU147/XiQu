package com.shumei.util;

import java.sql.*;

/**
 * JDBC的工具类
 */
public class DBUtil {

//Class.forName("com.mysql.jdbc.Driver");
//private static final String url = "jdbc:mysql://127.0.0.1:3306/tranditional";
private static final String url = "jdbc:mysql://127.0.0.1:3306/traditional?useSSL=false&serverTimezone=Asia/Shanghai";
private static final String user="root";
private static final String password="159357";
//private static final String driver="com.mysql.jdbc.Driver";
private static final String driver = "com.mysql.cj.jdbc.Driver"; // MySQL 8.x
    static {
        try {
            Class.forName(driver);
            System.out.println("MySQL 驱动加载成功");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驱动加载失败: " + e.getMessage());
        }
    }
//        Connection conn= DriverManager.getConnection(url,uname,pword);
    // 静态代码块，注册连接，因为它只需要注册一次，在类加载执行,注意要符合OCP原则
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接对象
     *
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 释放数据库资源的方法
     *
     * @param conn 连接对象
     * @param ps   数据库操作对象
     * @param rs   结果集对象
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
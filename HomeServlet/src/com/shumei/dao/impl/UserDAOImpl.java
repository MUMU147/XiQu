package com.shumei.dao.impl;

import com.shumei.dao.UserDAO;
import com.shumei.pojo.User;
import com.shumei.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

//    @Test
//    public void test() throws SQLException {
//        UserDAO userDAO=new UserDAOImpl();
//        List<User> Userlist = userDAO.getUsersList();
//        System.out.println(Userlist.size());
//    }
    @Override
    public List<User> getUsersList() {
        List<User> List =new ArrayList<>();
//        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
//            String sql = "select * from product";
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            re = ps.executeQuery();
            while (re.next()) {
//                int pid = re.getInt("pid");
//                String pname = re.getString("pname");
//                Double price = re.getDouble("price");
//                int stock = re.getInt("stock");
                int uid=re.getInt("uid");
                String username=  re.getString("username");
                String password =  re.getString("password");
                String email=re.getString("email");
                String nickname=re.getString("nickname");
//                System.out.println("uid: "+uid+'\t'+"uname: "+username+'\t'+"password: "+password+'\t'+"email: "+email+'\t'+"nickname"+nickname);
//                Product product = new Product();
//                product.setPid(re.getInt("pid"));
//                product.setPname(re.getString("pname"));
//                product.setPrice(re.getDouble("price"));
//                product.setStock(re.getInt("stock"));
                User user =new User();
                user.setUid(re.getInt("uid"));
                user.setUsername(re.getString("username"));
                user.setPassword(re.getString("password"));
                user.setEmail(re.getString("email"));
                user.setNickname(re.getString("nickname"));
                List.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return List;
    }
    @Override
    public boolean duser(int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from user where uid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, re);
        }
        return false;
    }
    @Override
    public void updateUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        //出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            //事务的try
            try {
                conn.setAutoCommit(false);
                String sql = "update user set username=?,password=?,email=?,nickname=? where uid=? ";
                //获取数据库预操作对象
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getNickname());
                ps.setInt(5, user.getUid());
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
//    public void addUser(User user) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet re = null;
//        try {
//            conn = DBUtil.getConnection();
//            try {
//                conn.setAutoCommit(false);
//                String sql = "INSERT INTO user(username,password,email,nickname) VALUE(?,?,?,?)";
//                ps = conn.prepareStatement(sql);
//                ps.setString(1, user.getUsername());
//                ps.setString(2, user.getPassword());
//                ps.setString(3, user.getEmail());
//                ps.setString(4, user.getNickname());
//                ps.executeUpdate();
//                conn.commit();
//            } catch (SQLException se) {
//                System.out.println(se.getMessage());
//                conn.rollback();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBUtil.close(conn, ps, re);
//        }
//    }
    public void addUser(User user) {
        //        System.out.println("注册时候的 dao user"+user.getUsername());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            //            事务
            conn.setAutoCommit(false);
            String sql = "insert into user(email,username,password) VALUES (?,?,?)"; // 添加了username字段
            // 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername()); // 添加了设置username的代码
            ps.setString(3,user.getPassword());
            //            System.out.println(user.getEmail());
            ps.executeUpdate();
            //        System.out.println(count);
            conn.commit();
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace(); // 打印堆栈跟踪
        } finally {
            DBUtil.close(conn,ps,rs); // 关闭资源应该在finally块中，以确保资源总是被关闭，即使在异常情况下也是如此。
        }
    }

@Override
    public User getuserByUid(Integer uid) {
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        User user = null;
        try {
            conn = DBUtil.getConnection();

            String sql = "SELECT uid,uname,password,email,nickname FROM user WHERE uid=?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            if (rs.next()){
              String uname=  rs.getString("uname");
              String password =  rs.getString("password");
              String email=rs.getString("email");
              String nickname=rs.getString("nickname");
              user = new User(uid,uname,password,email,nickname);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return user;
    }

@Override
    public List<User> getUserList(String keyword) {
        List<User> userslist = new ArrayList<>();
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT uid,username,password,email,nickname FROM user WHERE username like ?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                int uid=rs.getInt("uid");
                String uname=  rs.getString("username");
                String password =  rs.getString("password");
                String email=rs.getString("email");
                String nickname=rs.getString("nickname");
                userslist.add(new User(uid,uname,password,email,nickname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return userslist;
    }

    @Override
    public User selectUser(String username, String password) {
        User u = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setUid(rs.getInt("uid"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
            }else{
                u=null;
            }
            DBUtil.close(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public Boolean findUserByUsername(String userName) {
        User u = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean flag=false;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
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

    @Override
    public User findUserByEmail(String email){
        User u = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where email=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setUid(rs.getInt(1));
                u.setEmail(rs.getString(4));
            } else {
                u=null;
            }
            DBUtil.close(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}

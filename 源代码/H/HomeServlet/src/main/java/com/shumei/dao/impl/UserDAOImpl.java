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
    //获取所有用户列表
    public List<User> getUsersList() {
        List<User> List =new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            re = ps.executeQuery();
            while (re.next()) {
                int uid=re.getInt("uid");
                String username=  re.getString("username");
                String password =  re.getString("password");
                String email=re.getString("email");
                String nickname=re.getString("nickname");
                String phone=re.getString("phone");
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
                if (phone != null && "0".equalsIgnoreCase(phone)) {
                    // 如果phone在不区分大小写的情况下等于"0"，执行这里的代码
                    phone="无";
                    user.setPhone(phone);
                } else {
                    // 如果phone在不区分大小写的情况下不等于"0"，执行这里的代码
                    user.setPhone(re.getString("phone"));
                }
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
    //根据用户ID删除用户
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
    //    更新用户信息
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
                String sql = "update user set username=?,password=?,email=?,phone=?,nickname=? where uid=? ";
                //获取数据库预操作对象
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(5, user.getNickname());
                ps.setString(4, user.getPhone());
                ps.setInt(6, user.getUid());
                ps.executeUpdate();
                conn.commit();
                System.out.println("更新成功");
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
    //    添加新用户
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
    //根据用户ID获取用户信息
    public User getuserByUid(Integer uid) {
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        User user = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT uid,username,password,phone,email,nickname FROM user WHERE uid=?";
// 获取数据库预操作对象
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            if (rs.next()){
              String username=  rs.getString("username");
              String password =  rs.getString("password");
              String phone=rs.getString("phone");
              String email=rs.getString("email");
              String nickname=rs.getString("nickname");
              user = new User(uid,username,password,phone,email,nickname);
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
    //根据关键词获取用户列表
    public List<User> getUserList(String keyword) {
        List<User> userslist = new ArrayList<>();
// 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
// 出现错误，保证关闭的try
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user WHERE username like ?";
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
                String phone=rs.getString("phone");
                userslist.add(new User(uid,uname,password,phone,email,nickname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return userslist;
    }

    @Override
    //根据用户名和密码进行用户登录
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
    //    根据用户名查找用户
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
    //    监测用户名是否已注册，根据邮箱查找用户
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

    @Override
    //    监测用户名是否已注册，根据邮箱查找用户
    public User findUserByPhone(String phone){
        User u = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where phone=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
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

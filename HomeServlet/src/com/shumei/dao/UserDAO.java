package com.shumei.dao;

import com.shumei.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getUsersList();
    boolean duser(int uid);
    void updateUser(User user);
    void addUser(User user);
    User getuserByUid(Integer uid);
    List<User> getUserList(String keyword);
    User selectUser(String username, String password);
    //    登录
    Boolean findUserByUsername(String userName);
    //    监测用户名是否已注册
    User findUserByEmail(String email);
}

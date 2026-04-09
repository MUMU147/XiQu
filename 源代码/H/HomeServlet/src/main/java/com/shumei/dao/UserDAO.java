package com.shumei.dao;

import com.shumei.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getUsersList();      //获取所有用户列表
    boolean duser(int uid);     //根据用户ID删除用户
    void updateUser(User user); //    更新用户信息
    void addUser(User user);    //    添加新用户
    User getuserByUid(Integer uid);     //根据用户ID获取用户信息
    List<User> getUserList(String keyword);     //根据关键词获取用户列表
    User selectUser(String username, String password);      //根据用户名和密码进行用户登录
    Boolean findUserByUsername(String userName);        //    根据用户名查找用户
    User findUserByEmail(String email);     //    监测用户名是否已注册，根据邮箱查找用户
    User findUserByPhone(String phone);    //    监测用户名是否已注册，根据邮箱查找用户
}

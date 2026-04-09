package com.shumei.dao;

import com.shumei.pojo.Collection;
import com.shumei.pojo.Product;
import com.shumei.pojo.Ticket;
import com.shumei.pojo.User;


import java.util.List;

public interface CollectionDAO {
    void addCollection(Collection collection);  //向用户收藏夹中添加一个收藏集合
    List<Product> myCollection(User user);  //获取指定用户收藏夹中的所有收藏产品
    Boolean delCollection(int pid,int uid); //根据产品ID和用户ID删除用户收藏的产品
    Boolean findUserByCollectionPid(int pid,int uid);   //根据产品ID和用户ID查找用户是否收藏了该产品

}

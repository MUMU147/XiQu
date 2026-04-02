package com.shumei.dao;

import com.shumei.pojo.Collection;
import com.shumei.pojo.Product;
import com.shumei.pojo.User;


import java.util.List;

public interface CollectionDAO {
    void addCollection(Collection collection);
    List<Product> myCollection(User user);
    Boolean delCollection(int pid,int uid);

    Boolean findUserByCollectionPid(int pid,int uid);
}

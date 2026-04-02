package com.shumei.dao;

import com.shumei.pojo.Admin;

public interface AdminDAO {
    Admin getAdminUser(String username,String password);
}

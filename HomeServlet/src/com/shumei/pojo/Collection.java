package com.shumei.pojo;

import java.sql.Timestamp;
import com.shumei.pojo.Product;
public class Collection {
    private User user;
    private Product product;
    private Timestamp ctime;
    public Collection(){}
    public Collection(User user, Product product, Timestamp ctime) {
        this.user = user;
        this.product = product;
        this.ctime = ctime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }
}

package com.shumei.pojo;

public class Product {
    int pid;
    String pname;
    Double price;
    int stock;
    String img;

    public Product() {

    }

    public Product(int pid, String pname, Double price, int stock, String img) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.stock = stock;
        this.img = img;
    }
    public Product(String pname, Double price, int stock, String img){
        this.pname = pname;
        this.price = price;
        this.stock = stock;
        this.img = img;
    }

    public int getPid() {

        return pid;
    }

    public void setPid(int pid) {

        this.pid = pid;
    }

    public String getPname() {

        return pname;
    }

    public void setPname(String pname) {

        this.pname = pname;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public String getImg() {

        return img;
    }

    public void setImg(String img) {

        this.img = img;
    }

    public String toString() {
        return "Product{" + "pid=" + pid + ", pname='" + pname + '\'' + ", price=" + price + ", stock=" + stock + ", img='" + img + '\'' + '}';
    }
}

package com.shumei.pojo;

public class Order {
    int oid;
    String otime;
    int ophone;
    String own;
    String oaddress;
    Double omoney;
    public  Order(){}
    public Order(int oid, String otime, int ophone, String own, String oaddress, Double omoney) {
        this.oid = oid;
        this.otime = otime;
        this.ophone = ophone;
        this.own = own;
        this.oaddress = oaddress;
        this.omoney = omoney;
    }

    public Order(int oid, String otime, Double omoney) {
        this.oid = oid;
        this.otime = otime;
        this.omoney = omoney;
    }

    public String toString() {
        return "Order{" + "oid=" + oid + ", otime='" + otime + '\'' + "ophone=" + ophone + "own=" + own + ", oaddress='" + oaddress + '\''+ "omoney"+omoney+ '}';
    }

    public int getOid() {
        return oid;
    }

    public String getOtime() {
        return otime;
    }

    public int getOphone() {
        return ophone;
    }

    public String getOwn() {
        return own;
    }

    public String getOaddress() {
        return oaddress;
    }

    public Double getOmoney() {
        return omoney;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public void setOphone(int ophone) {
        this.ophone = ophone;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public void setOmoney(Double omoney) {
        this.omoney = omoney;
    }
}

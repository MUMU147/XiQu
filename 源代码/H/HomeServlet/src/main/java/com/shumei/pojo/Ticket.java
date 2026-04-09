package com.shumei.pojo;

public class Ticket {
    int ticID;
    String tname;
    Double tprice;
    String tkind;
    String timg;
    int tcount;
    public Ticket(){}
    public Ticket(int ticID, String tname, Double tprice, String tkind, String timg) {
        this.ticID = ticID;
        this.tname = tname;
        this.tprice = tprice;
        this.tkind = tkind;
        this.timg = timg;
    }
    public Ticket(String tname, Double tprice, int tcount, String timg) {
        this.tname = tname;
        this.tprice = tprice;
        this.tcount = tcount;
        this.timg = timg;
    }
    public Ticket(int ticID, String tname, String timg) {
        this.ticID = ticID;
        this.tname = tname;
        this.timg = timg;
    }

    public Ticket(int ticID, String tname, Double tprice, String tkind, String timg, int tcount) {
        this.ticID = ticID;
        this.tname = tname;
        this.tprice = tprice;
        this.tkind = tkind;
        this.timg = timg;
        this.tcount = tcount;
    }

    public Ticket(Integer ticID, String tname, Double tprice, int tcount, String timg) {
        this.ticID = ticID;
        this.tname = tname;
        this.tprice = tprice;
        this.timg = timg;
        this.tcount = tcount;
    }

    public int getTicID() {
        return ticID;
    }

    public void setTicID(int ticID) {
        this.ticID = ticID;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Double getTprice() {
        return tprice;
    }

    public void setTprice(Double tprice) {
        this.tprice = tprice;
    }

    public String getTkind() {
        return tkind;
    }

    public void setTkind(String tkind) {
        this.tkind = tkind;
    }

    public String getTimg() {
        return timg;
    }

    public void setTimg(String timg) {
        this.timg = timg;
    }

    public int getTcount() {
        return tcount;
    }

    public void setTcount(int tcount) {
        this.tcount = tcount;
    }
}

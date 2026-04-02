package com.shumei.pojo;

public class News {
    int nid;
    String subtime;
    String title;
    int click;
    public News(){}
    public News(int nid, String subtime, String title) {
        this.nid = nid;
        this.subtime = subtime;
        this.title = title;
    }
    public News(int nid, String subtime, String title, int click) {
        this.nid = nid;
        this.subtime = subtime;
        this.title = title;
        this.click = click;
    }

    public int getNid() {
        return nid;
    }

    public String getSubtime() {
        return subtime;
    }

    public String getTitle() {
        return title;
    }

    public int getClick() {
        return click;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setSubtime(String subtime) {
        this.subtime = subtime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClick(int click) {
        this.click = click;
    }
    public String toString() {
        return "News{" + "nid=" + nid + ", subtime='" + subtime + '\'' + ", title=" + title + ", click=" + click + '}';
    }
}

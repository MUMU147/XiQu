package com.shumei.pojo;

public class User {
    int uid;
    String username;
    String password;
    String email;
    String phone;
    String nickname;

    public User(int uid, String username, String password, String email, String phone, String nickname) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
    }

    public User(){}

    public User(int uid, String username, String password, String email, String nickname) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public User(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public User(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int phone, String email, String nickname) {
    }

    public String toString() {
        return "User{" + "uid=" + uid + ", uname='" + username + '\'' + ", psw=" + password + ", email=" + email + ", nickname='" + nickname + '\'' + '}';
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

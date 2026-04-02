package com.shumei.pojo;

public class Admin {
    private int adminID;
    private String username;
    private String password;
    private int role;
    public Admin(){

    }
    public Admin(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Admin(int adminID, String username, String password, int role) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

//    public String toString() {
//        return "Admin{" +
//                "adminID=" + adminID +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", role=" + role +
//                '}';
//    }
}

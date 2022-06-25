package com.example.aloparent;

public class UserModel {
    private  String userMail, userName;

    public UserModel(String userMail, String userName) {
        this.userMail = userMail;
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserName() {
        return userName;
    }
}

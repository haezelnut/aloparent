package com.example.aloparent.SharedRefrence;

public class UserModel {
    private  String userMail, userName, userPassword, userImage;

    public UserModel(String userMail, String userName, String userPassword, String userImage ) {
        this.userMail = userMail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userImage = userImage;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserImage() {
        return userImage;
    }
}

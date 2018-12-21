package com.example.vikash.recyclerview;

public class UserModel {

    public String userName;
    public String userDescription;
    public int userImage;

    public UserModel() {
    }

    public UserModel(String userName, String userDescription, int userImage) {
        this.userName = userName;

        this.userDescription = userDescription;

        this.userImage = userImage;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }


}

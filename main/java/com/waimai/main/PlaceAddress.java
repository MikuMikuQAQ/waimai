package com.waimai.main;

public class PlaceAddress {

    private int imgId;
    private String userName,userAddreess;

    public PlaceAddress(int imgId, String userName, String userAddreess) {
        this.imgId = imgId;
        this.userName = userName;
        this.userAddreess = userAddreess;
    }

    public int getImgId() {
        return imgId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddreess() {
        return userAddreess;
    }
}

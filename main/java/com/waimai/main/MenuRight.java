package com.waimai.main;

public class MenuRight {
    private int imgId;
    private String buyRightTitle;
    private String money;

    public MenuRight(int imgId, String buyRightTitle, String money) {
        this.imgId = imgId;
        this.buyRightTitle = buyRightTitle;
        this.money = money;
    }

    public int getImgId() {
        return imgId;
    }

    public String getBuyRightTitle() {
        return buyRightTitle;
    }

    public String getMoney() {
        return money;
    }
}

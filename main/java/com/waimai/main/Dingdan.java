package com.waimai.main;

public class Dingdan {
    private int imgId;
    private String shopName;
    private String foodName;
    private Double foodMoney;
    private String buyTime;
    private String status;

    public Dingdan(int imgId, String shopName, String foodName, Double foodMoney, String buyTime, String status) {
        this.imgId = imgId;
        this.shopName = shopName;
        this.foodName = foodName;
        this.foodMoney = foodMoney;
        this.buyTime = buyTime;
        this.status = status;
    }

    public int getImgId() {
        return imgId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getFoodName() {
        return foodName;
    }

    public Double getFoodMoney() {
        return foodMoney;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public String getStatus() {
        return status;
    }
}

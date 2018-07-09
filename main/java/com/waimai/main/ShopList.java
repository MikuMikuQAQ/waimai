package com.waimai.main;

public class ShopList {
    private int imgId;
    private String shopName;
    private String salesNum;
    private String category;
    private String condition;
    private String offer;

    public ShopList(int imgId, String shopName, String salesNum, String category, String condition, String offer) {
        this.imgId = imgId;
        this.shopName = shopName;
        this.salesNum = salesNum;
        this.category = category;
        this.condition = condition;
        this.offer = offer;
    }

    public int getImgId() {
        return imgId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getSalesNum() {
        return salesNum;
    }

    public String getCategory() {
        return category;
    }

    public String getCondition() {
        return condition;
    }

    public String getOffer() {
        return offer;
    }
}

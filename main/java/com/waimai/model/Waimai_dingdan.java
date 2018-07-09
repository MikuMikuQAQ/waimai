package com.waimai.model;

public class Waimai_dingdan {
    private int Id;
    private int status;
    private String shop;
    private double foodmoney;
    private int foodnum;
    private String buytime;

    public Waimai_dingdan() {
        super();
    }

    public Waimai_dingdan(int id, int status, String shop, double foodmoney, int foodnum, String buytime) {
        super();
        Id = id;
        this.status = status;
        this.shop = shop;
        this.foodmoney = foodmoney;
        this.foodnum = foodnum;
        this.buytime = buytime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public double getFoodmoney() {
        return foodmoney;
    }

    public void setFoodmoney(double foodmoney) {
        this.foodmoney = foodmoney;
    }

    public int getFoodnum() {
        return foodnum;
    }

    public void setFoodmun(int foodmun) {
        this.foodnum = foodmun;
    }

    public String getBuytime() {
        return buytime;
    }

    public void setBuytime(String buytime) {
        this.buytime = buytime;
    }
}

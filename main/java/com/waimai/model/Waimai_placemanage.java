package com.waimai.model;

public class Waimai_placemanage {
    private int Id;
    private String receiptuser;
    private String receiptplace;

    public Waimai_placemanage() {
        super();
    }

    public Waimai_placemanage(int id, String receiptuser, String receiptplace) {
        super();
        Id = id;
        this.receiptuser = receiptuser;
        this.receiptplace = receiptplace;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getReceiptuser() {
        return receiptuser;
    }

    public void setReceiptuser(String receiptuser) {
        this.receiptuser = receiptuser;
    }

    public String getReceiptplace() {
        return receiptplace;
    }

    public void setReceiptplace(String receiptplace) {
        this.receiptplace = receiptplace;
    }
}

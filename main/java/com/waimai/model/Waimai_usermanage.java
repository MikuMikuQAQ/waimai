package com.waimai.model;

public class Waimai_usermanage {

    private int Id;
    private String username;
    private String password;
    private double balance;
    private int integral;

    public Waimai_usermanage() {
        super();
    }

    public Waimai_usermanage(int id, String username, String password, double balance, int integral) {
        super();
        Id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.integral = integral;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}

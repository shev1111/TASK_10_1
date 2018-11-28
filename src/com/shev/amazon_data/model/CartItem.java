package com.shev.amazon_data.model;

public class CartItem {
    private int item_order_id;
    private String item_asin;
    private String user_login;
    private String times;

    public CartItem(int item_order_id, String item_asin, String user_login, String times) {
        this.item_order_id = item_order_id;
        this.item_asin = item_asin;
        this.user_login = user_login;
        this.times = times;
    }

    public CartItem(String item_asin, String user_login) {
        this.item_asin = item_asin;
        this.user_login = user_login;
    }

    public CartItem() {
    }

    public int getItem_order_id() {
        return item_order_id;
    }

    public void setItem_order_id(int item_order_id) {
        this.item_order_id = item_order_id;
    }

    public String getItem_asin() {
        return item_asin;
    }

    public void setItem_asin(String item_asin) {
        this.item_asin = item_asin;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}

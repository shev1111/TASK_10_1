package com.shev.amazon_data.model;

public class User {
    private String login;
    private String user_name;
    private String pasword;
    private String user_id;

    public User() {
    }

    public User(String login, String user_name, String pasword) {
        this.login = login;
        this.user_name = user_name;
        this.pasword = pasword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

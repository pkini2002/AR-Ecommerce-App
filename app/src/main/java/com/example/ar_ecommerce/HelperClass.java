package com.example.ar_ecommerce;

public class HelperClass {
    String Password,username;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HelperClass(String password, String username) {
        this.Password = password;
        this.username = username;
    }



    public HelperClass() {
    }
}


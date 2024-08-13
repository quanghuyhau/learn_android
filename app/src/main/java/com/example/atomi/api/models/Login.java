package com.example.atomi.api.models;

import androidx.databinding.Bindable;

public class Login {
    private String username;
    private String password;
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

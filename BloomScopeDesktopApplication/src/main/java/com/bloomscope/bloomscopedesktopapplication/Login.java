package com.bloomscope.bloomscopedesktopapplication;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
    private String username;
    private String password;

    public Login() {
    }

    public Login(TextField userName, PasswordField password) {
        this.username = userName.getText();
        this.password = password.getText();
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
}

package com.bloomscope.bloomscopedesktopapplication;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Parent {
    private String confirmPassword;
    private String createPassword;
    private String phone;
    private String email;
    private String name;

    public Parent(TextField confirmPassword, PasswordField createPassword, TextField phone, TextField email, TextField name) {
        this.confirmPassword = confirmPassword.getText();
        this.createPassword = createPassword.getText();
        this.phone = phone.getText();
        this.email = email.getText();
        this.name = name.getText();
    }


    @Override
    public String toString() {
        return "Parent{" +
                "confirmPassword='" + confirmPassword + '\'' +
                ", createPassword='" + createPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCreatePassword() {
        return createPassword;
    }

    public void setCreatePassword(String createPassword) {
        this.createPassword = createPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

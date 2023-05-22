package com.bloomscope.bloomscopedesktopapplication;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Organizaion {
    public String confirmPassword;
    public String createPassword;
    public String phone;
    public String email;
    public String name;
    private String docPath;

    private File file;
    private byte[] fileContent;


    public Organizaion() {
    }

    public Organizaion(TextField confirmPassword, PasswordField createPassword, TextField phone, TextField email, TextField name, TextField docPath){
        this.confirmPassword = confirmPassword.getText();
        this.createPassword = createPassword.getText();
        this.phone = phone.getText();
        this.email = email.getText();
        this.name = name.getText();
        this.docPath = docPath.getText();
        this.file = new File(this.docPath);
        try {
            this.fileContent = Files.readAllBytes(this.file.toPath());
        } catch (IOException e) {
            System.out.println("Exception:\n calss: Organization \n Method: constructor");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Organizaion{" +
                "confirmPassword='" + confirmPassword + '\'' +
                ", createPassword='" + createPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", docPath='" + docPath + '\'' +
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

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }
}

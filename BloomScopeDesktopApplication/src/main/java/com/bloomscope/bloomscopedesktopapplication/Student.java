package com.bloomscope.bloomscopedesktopapplication;

//import com.google.gson.Gson;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Student {
    private String name;
    private String emailId;
    private String phoneNo;
    private String dateOfBirth;
    private String parentEmail;
    private String parentPhone;
    private String createPass;
    private String confirmPass;
    private String docPath;

    private File file;
    private byte[] fileContent;

    public Student() {
    }

    public Student(TextField name, TextField emailId, TextField phoneNo, DatePicker tateOfBirth, TextField parentEmail, TextField parentPhone, PasswordField createPass, TextField confirmPass, TextField docPath) throws IOException {
        this.name = name.getText();
        this.emailId = emailId.getText();
        this.phoneNo = phoneNo.getText();
        this.dateOfBirth = tateOfBirth.getValue().toString();
        this.parentEmail = parentEmail.getText();
        this.parentPhone = parentPhone.getText();
        this.createPass = createPass.getText();
        this.confirmPass = confirmPass.getText();
        this.docPath = docPath.getText();//"/Users/narenderkumarchoudhary/Desktop/bloomScopeLogoIIT.jpg";
        this.file = new File(this.docPath);
        this.fileContent = Files.readAllBytes(this.file.toPath());
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                ", parentPhone='" + parentPhone + '\'' +
                ", createPass='" + createPass + '\'' +
                ", confirmPass='" + confirmPass + '\'' +
                ", docPath='" + docPath + '\'' +
                '}';
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTateOfBirth() {
        return dateOfBirth;
    }

    public void setTateOfBirth(String tateOfBirth) {
        this.dateOfBirth = tateOfBirth;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getCreatePass() {
        return createPass;
    }

    public void setCreatePass(String createPass) {
        this.createPass = createPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public byte[] getFileContent() {
        return fileContent;
    }
}

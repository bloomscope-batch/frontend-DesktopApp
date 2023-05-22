package com.bloomscope.bloomscopedesktopapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentController {
    public TextField name;
    public TextField emailId;
    public TextField phoneNo;
    public DatePicker dateOfBirth;
    public TextField parentEmail;
    public TextField parentPhone;
    public PasswordField createPass;
    public TextField confirmPass;

    @FXML
    TextField docPath;

    @FXML
    void chooseFile(MouseEvent event) {
        docPath.clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Document");
        Stage primaryStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        docPath.appendText(selectedFile.getPath());
    }

    @FXML
    void goToLoginPage(MouseEvent event) {
        loadPage("login");
    }

    private void loadPage(String page) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            VBox vbox = (VBox) fxmlLoader.load();

            Static.home.getChildren().clear();

            Static.home.getChildren().addAll(vbox.getChildren());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUp(MouseEvent mouseEvent) {
        Student student;
        try {
            student = new Student(name, emailId, phoneNo, dateOfBirth, parentEmail, parentPhone, createPass, confirmPass, docPath);
//            System.out.println(student.toString());
        }
        catch (Exception e) {
            System.out.println("Please fill all fields..");
            return;
        }

        Static staticObj = new Static<>();
        String jsonRespons = staticObj.makeHTTPrequest(student, "POST", "http://localhost:8080/demo/post");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            StudentSignUpResponse studentSignUpResponse = objectMapper.readValue(jsonRespons, StudentSignUpResponse.class);
//            System.out.println(studentSignUpResponse.getStatus());
//            System.out.println(studentSignUpResponse.getMessage());

            staticObj.loadPage("login");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

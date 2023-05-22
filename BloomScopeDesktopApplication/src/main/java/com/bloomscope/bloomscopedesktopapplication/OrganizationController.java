package com.bloomscope.bloomscopedesktopapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OrganizationController {
    public TextField confirmPassword;
    public PasswordField createPassword;
    public TextField phone;
    public TextField email;
    public TextField name;
    @FXML
    private TextField docPath;

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
        System.out.println("Org.Signup");
        Organizaion organizaion = new Organizaion(confirmPassword, createPassword, phone, email, name, docPath);
        System.out.println(organizaion.toString());

        Static<Organizaion> staticObj = new Static<>();
        String jsonResponse = staticObj.makeHTTPrequest(organizaion, "POST", "http://localhost:8080/demo/post/organizationsingup");

        ObjectMapper objectMapper = new ObjectMapper();
        OrganizationSignUpResponse organizationSignUpResponse = null;
        try {
            organizationSignUpResponse = objectMapper.readValue(jsonResponse, OrganizationSignUpResponse.class);
            System.out.println(organizationSignUpResponse.getStatus());
            System.out.println(organizationSignUpResponse.getMessage());

            staticObj.loadPage("login");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.bloomscope.bloomscopedesktopapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ParentController {

    public TextField confirmPassword;
    public PasswordField createPassword;
    public TextField phone;
    public TextField email;
    public TextField name;

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
        System.out.println("clicked: ");
        Parent parent = new Parent(confirmPassword, createPassword, phone, email, name);

        Static staticObj = new Static<ParentSignUpResponse>();
        String jsonResponss = staticObj.makeHTTPrequest(parent, "POST", "http://localhost:8080/demo/post/parentsingup");
        ObjectMapper mapper = new ObjectMapper();

        try {
            ParentSignUpResponse parentSignUpResponse = mapper.readValue(jsonResponss, ParentSignUpResponse.class);
            System.out.println(parentSignUpResponse.getStatus());
            System.out.println(parentSignUpResponse.getMessage());

            staticObj.loadPage("login");
        } catch (JsonProcessingException e) {
            System.out.println("Exception: \n calss: ParentController \n Metho: SignUp");
            throw new RuntimeException(e);
        }
    }
}

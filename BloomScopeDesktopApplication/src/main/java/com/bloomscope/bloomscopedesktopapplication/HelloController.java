package com.bloomscope.bloomscopedesktopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private VBox registerHome;
    @FXML
    TextField docPath;
    @FXML
    VBox vbox;
    @FXML
    ComboBox<String> chooseUser = new ComboBox<>();

    @FXML
    private Button file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectRegistrationUser();
    }

    @FXML
    void chooseFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Document");
        Stage primaryStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        docPath.appendText(selectedFile.getPath());
    }


    public void selectRegistrationUser() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Student");
        list.add("Parent");
        list.add("Organization");
        chooseUser.setItems(list);
    }



    private void loadPage(String page) {
        if(registerHome == null) {
            System.out.println("registerHome is null: "+registerHome);
            return;
        }
        try {
            System.out.println("Trying to load: " + page);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            VBox pageVBox = (VBox) loader.load();
            registerHome.getChildren().clear();
            registerHome.getChildren().addAll(pageVBox.getChildren());
            System.out.println(registerHome);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void goToLoginPage(MouseEvent mouseEvent) {
        loadPage("login");
    }


    public void goToRegisterHome(MouseEvent mouseEvent) {
        loadPage("registerHome");
    }

    public void goToCreatePassword(MouseEvent mouseEvent) {
        loadPage("registerForgotPasswordNext");
    }

    public void goToForgotPassword(MouseEvent mouseEvent) {
        loadPage("registerForgotPassword");
    }
}

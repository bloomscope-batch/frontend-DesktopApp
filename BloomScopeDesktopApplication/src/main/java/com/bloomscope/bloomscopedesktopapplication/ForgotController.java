package com.bloomscope.bloomscopedesktopapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ForgotController {

    @FXML
    private Text login;

    @FXML
    void goToCreatePassword(MouseEvent event) {
        loadPage("registerForgotPasswordNext");
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

}

package com.bloomscope.bloomscopedesktopapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public VBox home;
    public ScrollPane homeScrolPan;
    public BorderPane homeBorderPan;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Static.homeBorderPan = homeBorderPan;
        Static.homeScrolPan = homeScrolPan;
        Static.home = home;
        loadPage("login");
    }



    private void loadPage(String page) {
        try {
            FXMLLoader page2Loader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            System.out.println(page2Loader.getLocation());
            VBox page2VBox = (VBox) page2Loader.load();
            home.getChildren().clear();
            home.getChildren().addAll(page2VBox.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

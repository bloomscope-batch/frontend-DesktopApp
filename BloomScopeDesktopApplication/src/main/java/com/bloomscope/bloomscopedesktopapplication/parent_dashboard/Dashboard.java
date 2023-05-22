package com.bloomscope.bloomscopedesktopapplication.parent_dashboard;

import com.bloomscope.bloomscopedesktopapplication.HelloApplication;
import com.bloomscope.bloomscopedesktopapplication.Static;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    public Circle circle;

    public void logOut(MouseEvent mouseEvent) {
        HelloApplication.loadXML("home.fxml", false);
//        Static staticObj = new Static();
//        staticObj.loadPage("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("avatar.jpg");
        circle.setFill(new ImagePattern(image));
    }
}

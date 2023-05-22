package com.bloomscope.bloomscopedesktopapplication.student_dashboard;

import com.bloomscope.bloomscopedesktopapplication.HelloApplication;
import com.bloomscope.bloomscopedesktopapplication.Static;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    public ImageView avatar;
    public Circle circle;
    public ScrollPane studentDashboard;

    public void logOut(MouseEvent mouseEvent) {
        HelloApplication.loadXML("home.fxml", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("avatar.jpg");
        circle.setFill(new ImagePattern(image));
    }
}

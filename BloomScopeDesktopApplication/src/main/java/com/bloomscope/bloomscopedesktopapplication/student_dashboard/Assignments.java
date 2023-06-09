package com.bloomscope.bloomscopedesktopapplication.student_dashboard;

import com.bloomscope.bloomscopedesktopapplication.Static;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Assignments implements Initializable {
    public VBox vb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));vb.getChildren().add(new Label("1"));
//        vb.getChildren().add(new Label("1"));


    }

    private boolean loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page + ".fxml"));
        try {
            ScrollPane scrollPane = (ScrollPane) fxmlLoader.load();
            Static.studentDashboard.setContent(scrollPane.getContent());
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllAssignments(MouseEvent mouseEvent) {
        loadPage("AllAssignments");
    }
}

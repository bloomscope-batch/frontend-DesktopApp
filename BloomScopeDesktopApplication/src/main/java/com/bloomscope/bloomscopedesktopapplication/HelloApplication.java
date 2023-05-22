package com.bloomscope.bloomscopedesktopapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage primaryStage;
    public static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        loadXML("home.fxml", true);
//        loadXML("/com/bloomscope/bloomscopedesktopapplication/student_dashboard/dashboard.fxml");

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/bloomscope/bloomscopedesktopapplication/student_dashboard/dashboard.fxml"));
//        try {
//            scene = new Scene(fxmlLoader.load());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        stage.setTitle("BloomScope");
//        stage.setScene(scene);
//        stage.setMinHeight(600);
//        stage.setMinWidth(800);
//        stage.setFullScreen(true);
////        System.out.println(stage.getStyle());
//        System.out.println(stage.isFullScreen());
//        stage.show();
    }

    public static void loadXML(String page, boolean isFullScreen){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            scene = new Scene(fxmlLoader.load());
            Static.scene = scene;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("BloomScope");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
//        primaryStage.setFullScreen(isFullScreen);
//        System.out.println(stage.getStyle());
//        System.out.println(primaryStage.isFullScreen());
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
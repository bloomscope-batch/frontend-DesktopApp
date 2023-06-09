package com.bloomscope.bloomscopedesktopapplication.student_dashboard;

import com.bloomscope.bloomscopedesktopapplication.HelloApplication;
import com.bloomscope.bloomscopedesktopapplication.Static;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    public ImageView avatar;
    public Circle circle;
    public ScrollPane studentDashboard;
    public Text helpDisk;
    public Text overallProgressReport;
    public Text suggestions;
    public Text calendar;
    public Text assignments;
    public Text latestUpdates;

    public void logOut(MouseEvent mouseEvent) {
        HelloApplication.loadXML("home.fxml", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Static.studentDashboard = studentDashboard;
        Image image = new Image("avatar.jpg");
        circle.setFill(new ImagePattern(image));
        boolean pageLoaded = loadPage("Updates");
        if(pageLoaded) changeColour(latestUpdates);
    }
    private boolean loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page + ".fxml"));
        try {
            ScrollPane scrollPane = (ScrollPane) fxmlLoader.load();
            studentDashboard.setContent(scrollPane.getContent());
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getUpdates(MouseEvent mouseEvent) {
//        System.out.println("getUpdates");
        boolean pageLoaded = loadPage("Updates");
        if(pageLoaded) changeColour(latestUpdates);
    }

    private void changeColour(Text clicked) {
        defaultColour();
        clicked.getStyleClass().add("sidebar-clicked-colour");
    }

    private void defaultColour() {
        latestUpdates.getStyleClass().clear();
        assignments.getStyleClass().clear();
        calendar.getStyleClass().clear();
        suggestions.getStyleClass().clear();
        overallProgressReport.getStyleClass().clear();
        helpDisk.getStyleClass().clear();

        helpDisk.getStyleClass().add("sidebar-default-colour");
        overallProgressReport.getStyleClass().add("sidebar-default-colour");
        suggestions.getStyleClass().add("sidebar-default-colour");
        calendar.getStyleClass().add("sidebar-default-colour");
        assignments.getStyleClass().add("sidebar-default-colour");
        latestUpdates.getStyleClass().add("sidebar-default-colour");
    }


    public void getAssignments(MouseEvent mouseEvent) {
        boolean pageLoaded = loadPage("Assignments");
        if(pageLoaded) changeColour(assignments);
    }

    public void getCallender(MouseEvent mouseEvent) {
        boolean pageLoaded = loadPage("Calendar");
        if(pageLoaded) changeColour(calendar);
    }

    public void getSuggestions(MouseEvent mouseEvent) {
        boolean pageLoaded = loadPage("Suggestions");
        if(pageLoaded) changeColour(suggestions);
    }

    public void getProgressReport(MouseEvent mouseEvent) {
        boolean pageLoaded = loadPage("OverallProgressReport");
        if(pageLoaded) changeColour(overallProgressReport);
    }

    public void getHelpDisk(MouseEvent mouseEvent) {
        boolean pageLoaded = loadPage("Helpdisk");
        if(pageLoaded) changeColour(helpDisk);
    }
}

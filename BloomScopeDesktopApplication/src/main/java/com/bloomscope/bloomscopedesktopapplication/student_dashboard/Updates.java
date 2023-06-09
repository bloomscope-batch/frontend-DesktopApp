package com.bloomscope.bloomscopedesktopapplication.student_dashboard;

import com.bloomscope.bloomscopedesktopapplication.Static;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class Updates implements Initializable {
    public VBox vbox;
    private Label date;
    private Label updateHeading;
    private Label update;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpdate();
        setUpdate();
        setUpdate();
        setUpdate();

    }

    private void setUpdate() {
        VBox newVbox = new VBox();
        date = new Label("07/06/2023");
        updateHeading = new Label("New Heading");
        update = new Label("melius egestas appetere ornare et platonem dolorum his utinam quem quisque his dictum menandri ceteros natum pro melius regione duis nominavi potenti mauris pertinacia tristique adolescens fuisset option tempus utroque adversarium cum veritus interpretaris consectetur doctus latine nostra habeo inani habeo ocurreret rhoncus accusata esse sollicitudin maecenas ea latine fusce");
        update.setWrapText(true);
        date.getStyleClass().add("date-style");
        updateHeading.getStyleClass().add("updateHeading-style");
        update.getStyleClass().add("update-style");

        newVbox.getChildren().add(date);
        newVbox.getChildren().add(updateHeading);
        newVbox.getChildren().add(update);
        newVbox.getStyleClass().add("update-vbox");
        vbox.getChildren().add(newVbox);
        Static.scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }
}

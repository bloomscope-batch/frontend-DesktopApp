
package com.bloomscope.bloomscopedesktopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ComboBox<String> chooseUser;

    @FXML
    public void goToChoosedUser(MouseEvent mouseEvent) {
        String s = chooseUser.getValue();
        if (s.equals("Student")) loadPage("registerStudent");
        if (s.equals("Parent")) loadPage("registerParent");
        if (s.equals("Organization")) loadPage("registerOrganization");
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectRegistrationUser();
    }

    public void selectRegistrationUser() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Student");
        list.add("Parent");
        list.add("Organization");
        chooseUser.setItems(list);
    }
}

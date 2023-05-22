package com.bloomscope.bloomscopedesktopapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.*;

public class LoginController {
    public TextField userName;
    public PasswordField password;

    @FXML
    void goToForgotPassword(MouseEvent event) {
        loadPage("registerForgotPassword");
    }

    @FXML
    void goToRegisterHome(MouseEvent event) {
        loadPage("registerHome");
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

    public void signIn(MouseEvent mouseEvent) throws IOException {
        boolean isAllInputGiven = checkInuts();
        if(!isAllInputGiven) return;
        Login login = new Login(userName, password);

        Static<Login> staticObj = new Static();
        String jsonResponse = staticObj.makeHTTPrequest(login, "POST", "http://127.0.0.1:5000/auth/login/");

        ObjectMapper objectMapper = new ObjectMapper();
        LoginResponse loginResponse = objectMapper.readValue(jsonResponse, LoginResponse.class);

        System.out.println("userType: "+loginResponse.getUserType());
        System.out.println("message: " + loginResponse.getMessage());
        if(loginResponse.getMessage().contains("authorized")) goToPage(loginResponse);
    }

    private void goToPage(LoginResponse loginResponse) {
//        System.out.println(loginResponse.getMessage());

        if(loginResponse.getUserType().toLowerCase().contains("student")) {
            loadDashboard("/com/bloomscope/bloomscopedesktopapplication/student_dashboard/student_dashboard.fxml");
        }
        else if(loginResponse.getUserType().toLowerCase().contains("parent")) {
            loadDashboard("/com/bloomscope/bloomscopedesktopapplication/parent_dashboard/parent_dashboard.fxml");
//            System.out.println("lode Parent dashboar page");
//            loadPage("/com/bloomscope/bloomscopedesktopapplication/parent_dashboard/dashboard");
        }
        else if(loginResponse.getUserType().toLowerCase().contains("organization")) {
            loadDashboard("/com/bloomscope/bloomscopedesktopapplication/organization_dashboard/organization_dashboard.fxml");
//            System.out.println("lode Organization dashboar page");
//            loadPage("/com/bloomscope/bloomscopedesktopapplication/organization_dashboard/dashboard");
        }
    }

    private void loadDashboard(String page) {
        try {
//            homeScrolPan.getChildrenUnmodifiable().clear();
            FXMLLoader page2Loader = new FXMLLoader(getClass().getResource(page));
//            System.out.println(page2Loader.getLocation());
            ScrollPane scrollPane = (ScrollPane) page2Loader.load();
            Static.homeScrolPan.setContent(scrollPane.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkInuts() {
        if(userName.getText().length() == 0 || password.getText().length() == 0) return false;
        return true;
    }
}


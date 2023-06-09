package com.bloomscope.bloomscopedesktopapplication;

public class LoginResponse {
//    private int status;
    private String userType;
    private String message;

    public LoginResponse() {
    }

    public LoginResponse(int status, String userType, String message) {
        this.userType = userType;
        this.message = message;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
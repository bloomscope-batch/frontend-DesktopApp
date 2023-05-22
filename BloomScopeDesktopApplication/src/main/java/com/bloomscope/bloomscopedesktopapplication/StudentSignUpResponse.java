package com.bloomscope.bloomscopedesktopapplication;

import java.io.File;

public class StudentSignUpResponse {
    private String status;
    private String message;

    public StudentSignUpResponse() {
    }

    public StudentSignUpResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

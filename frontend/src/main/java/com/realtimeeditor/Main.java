package com.realtimeeditor;

import com.realtimeeditor.controller.LoginController;
import com.realtimeeditor.service.LoginService;
import com.realtimeeditor.view.LoginView;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginService loginService = new LoginService();
        LoginController loginController = new LoginController(loginView, loginService);
    }
}
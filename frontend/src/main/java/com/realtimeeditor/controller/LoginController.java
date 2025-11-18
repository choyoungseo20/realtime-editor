package com.realtimeeditor.controller;

import com.realtimeeditor.model.LoginInfo;
import com.realtimeeditor.service.LoginService;
import com.realtimeeditor.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {

    private final LoginView loginView;
    private final LoginService loginService;

    public LoginController(LoginView loginView, LoginService loginService) {
        this.loginView = loginView;
        this.loginService = loginService;
        loginView.setVisible(true);
        loginView.setLoginActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nickname = loginView.getNickname();
        String password = loginView.getPassword();
        LoginInfo loginInfo = new LoginInfo(nickname, password);
        boolean success = loginService.login(loginInfo);

        if (success) {
            JOptionPane.showMessageDialog(loginView, "로그인 성공");
        } else {
            JOptionPane.showMessageDialog(loginView, "로그인 실패");
        }
    }
}

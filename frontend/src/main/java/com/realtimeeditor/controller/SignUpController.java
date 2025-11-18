package com.realtimeeditor.controller;

import com.realtimeeditor.model.SignUpInfo;
import com.realtimeeditor.service.LoginService;
import com.realtimeeditor.service.SignUpService;
import com.realtimeeditor.view.LoginView;
import com.realtimeeditor.view.SignUpView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SignUpController implements ActionListener {

    private final SignUpView signUpView;
    private final SignUpService signUpService;

    public SignUpController(SignUpView signUpView, SignUpService signUpService) {
        this.signUpView = signUpView;
        this.signUpService = signUpService;
        signUpView.setVisible(true);
        signUpView.setCancelActionListener(this);
        signUpView.setSignUpActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == signUpView.getCancelButton()) {
            navigateToLogin();
        } else if (source == signUpView.getSignUpButton()) {
            boolean success = handleSignUp();
            if (success) {
                navigateToLogin();
            }
        }
    }

    private void navigateToLogin() {
        LoginView loginView = new LoginView();
        LoginService loginService = new LoginService();
        LoginController loginController = new LoginController(loginView, loginService);
        signUpView.dispose();
    }

    private boolean handleSignUp() {
        SignUpInfo signUpInfo = getSignUpInfoFromView();
        if (signUpInfo == null) {
            return false;
        }

        boolean success = signUpService.signUp(signUpInfo);
        if (success) {
            JOptionPane.showMessageDialog(signUpView, "회원가입 성공");
            return true;
        } else {
            JOptionPane.showMessageDialog(signUpView, "회원가입 실패");
            return false;
        }
    }

    private SignUpInfo getSignUpInfoFromView() {
        String nickname = signUpView.getNickname();
        String password = signUpView.getPassword();
        String passwordConfirm = signUpView.getPasswordConfirm();

        try {
            SignUpInfo signUpInfo = new SignUpInfo(nickname, password, passwordConfirm);
            return signUpInfo;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(signUpView, e.getMessage());
            return null;
        }
    }
}

package com.realtimeeditor.view;

import com.realtimeeditor.view.panel.AuthButtonPanel;
import com.realtimeeditor.view.panel.LoginInputPanel;
import com.realtimeeditor.view.panel.ServiceHeaderPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LoginView extends JFrame {

    private ServiceHeaderPanel serviceHeaderPanel;
    private LoginInputPanel loginInputPanel;
    private AuthButtonPanel authButtonPanel;

    public LoginView() {
        initWindow();
        initComponents();
    }

    public void setNavigateSignUpActionListener(ActionListener actionListener) {
        authButtonPanel.setNavigateSignUpActionListener(actionListener);
    }

    public void setLoginActionListener(ActionListener actionListener) {
        authButtonPanel.setLoginActionListener(actionListener);
    }

    public JButton getSignUpButton() {
        return authButtonPanel.getNavigateSignUpButton();
    }

    public JButton getLoginButton() {
        return authButtonPanel.getLoginButton();
    }

    public String getNickname() {
        return loginInputPanel.getNickname();
    }

    public String getPassword() {
        return loginInputPanel.getPassword();
    }

    private void initWindow() {
        setTitle("Realtime Editor");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        // getContentPane().setBackground(new Color(30, 31, 34));
    }

    private void initComponents() {
        serviceHeaderPanel = new ServiceHeaderPanel();
        add(serviceHeaderPanel, BorderLayout.NORTH);

        loginInputPanel = new LoginInputPanel();
        add(loginInputPanel, BorderLayout.CENTER);

        authButtonPanel = new AuthButtonPanel();
        add(authButtonPanel, BorderLayout.SOUTH);
    }
}

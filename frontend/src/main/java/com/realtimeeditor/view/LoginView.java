package com.realtimeeditor.view;

import com.realtimeeditor.view.panel.LoginButtonPanel;
import com.realtimeeditor.view.panel.LoginInputPanel;
import com.realtimeeditor.view.panel.ServiceNamePanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LoginView extends JFrame {

    private ServiceNamePanel serviceNamePanel;
    private LoginInputPanel loginInputPanel;
    private LoginButtonPanel loginButtonPanel;

    public LoginView() {
        initWindow();
        initComponents();
    }

    public void setLoginActionListener(ActionListener actionListener) {
        loginButtonPanel.setLoginActionListener(actionListener);
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
        serviceNamePanel = new ServiceNamePanel();
        add(serviceNamePanel, BorderLayout.NORTH);

        loginInputPanel = new LoginInputPanel();
        add(loginInputPanel, BorderLayout.CENTER);

        loginButtonPanel = new LoginButtonPanel();
        add(loginButtonPanel, BorderLayout.SOUTH);
    }
}

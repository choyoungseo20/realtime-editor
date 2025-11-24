package com.realtimeeditor.view;

import com.realtimeeditor.view.panel.SignUpButtonPanel;
import com.realtimeeditor.view.panel.SignUpHeaderPanel;
import com.realtimeeditor.view.panel.SignUpInputPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SignUpView extends JFrame {

    private SignUpHeaderPanel signUpHeaderPanel;
    private SignUpInputPanel signUpInputPanel;
    private SignUpButtonPanel signUpButtonPanel;

    public SignUpView() {
        initWindow();
        initComponents();
    }

    private void initWindow() {
        setTitle("Realtime Editor");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        // getContentPane().setBackground(new Color(30, 31, 34));
    }

    public void setCancelActionListener(ActionListener actionListener) {
        signUpButtonPanel.setCancelActionListener(actionListener);
    }

    public void setSignUpActionListener(ActionListener actionListener) {
        signUpButtonPanel.setSignUpActionListener(actionListener);
    }

    public JButton getCancelButton() {
        return signUpButtonPanel.getCancelButton();
    }

    public JButton getSignUpButton() {
        return signUpButtonPanel.getSignUpButton();
    }

    public String getNickname() {
        return signUpInputPanel.getNickname();
    }

    public String getPassword() {
        return signUpInputPanel.getPassword();
    }

    public String getPasswordConfirm() {
        return signUpInputPanel.getPasswordConfirm();
    }

    private void initComponents() {
        signUpHeaderPanel = new SignUpHeaderPanel();
        add(signUpHeaderPanel, BorderLayout.NORTH);

        signUpInputPanel = new SignUpInputPanel();
        add(signUpInputPanel, BorderLayout.CENTER);

        signUpButtonPanel = new SignUpButtonPanel();
        add(signUpButtonPanel, BorderLayout.SOUTH);
    }
}

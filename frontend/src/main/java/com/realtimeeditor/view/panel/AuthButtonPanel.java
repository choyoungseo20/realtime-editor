package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AuthButtonPanel extends JPanel {

    private final JButton navigateSignUpButton;
    private final JButton loginButton;

    public AuthButtonPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        navigateSignUpButton = new JButton("회원가입");
        navigateSignUpButton.setFont(UIFont.EXPLAIN);

        loginButton = new JButton("로그인");
        loginButton.setFont(UIFont.EXPLAIN);

        add(navigateSignUpButton);
        add(loginButton);
    }

    public JButton getNavigateSignUpButton() {
        return navigateSignUpButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setNavigateSignUpActionListener(ActionListener actionListener) {
        navigateSignUpButton.addActionListener(actionListener);
    }

    public void setLoginActionListener(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }
}

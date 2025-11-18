package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginButtonPanel extends JPanel {

    private final JButton loginButton;

    public LoginButtonPanel() {
        loginButton = new JButton("Login");
        loginButton.setFont(UIFont.EXPLAIN);

        add(loginButton);
    }

    public void setLoginActionListener(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }
}

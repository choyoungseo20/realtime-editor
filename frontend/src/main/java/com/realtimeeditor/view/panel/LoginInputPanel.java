package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginInputPanel extends JPanel {

    private final JTextField txtNickname;
    private final JPasswordField txtPassword;

    public LoginInputPanel() {
        JPanel loginInputPanel = new JPanel();
        loginInputPanel.setLayout(new GridBagLayout());

        JLabel lblNickname = new JLabel("닉네임: ");
        lblNickname.setFont(UIFont.EXPLAIN);
        txtNickname = new JTextField(15);
        txtNickname.setFont(UIFont.INPUT);

        JLabel lblPassword = new JLabel("비밀번호: ");
        lblPassword.setFont(UIFont.EXPLAIN);
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(UIFont.INPUT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginInputPanel.add(lblNickname, gbc);
        gbc.gridx = 1;
        loginInputPanel.add(txtNickname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginInputPanel.add(lblPassword, gbc);
        gbc.gridx = 1;
        loginInputPanel.add(txtPassword, gbc);

        add(loginInputPanel);
    }

    public String getNickname() {
        return txtNickname.getText();
    }

    public String getPassword() {
        char[] p = txtPassword.getPassword();
        return new String(p);
    }
}

package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpInputPanel extends JPanel {

    private final JTextField txtNickname;
    private final JPasswordField txtPassword;
    private final JPasswordField txtPasswordConfirm;

    public SignUpInputPanel() {
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

        JLabel lblPasswordConfirm = new JLabel("비밀번호 확인: ");
        lblPasswordConfirm.setFont(UIFont.EXPLAIN);
        txtPasswordConfirm = new JPasswordField(15);
        txtPasswordConfirm.setFont(UIFont.INPUT);

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

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginInputPanel.add(lblPasswordConfirm, gbc);
        gbc.gridx = 1;
        loginInputPanel.add(txtPasswordConfirm, gbc);

        add(loginInputPanel);
    }

    public String getNickname() {
        return txtNickname.getText();
    }

    public String getPassword() {
        char[] p = txtPassword.getPassword();
        return new String(p);
    }

    public String getPasswordConfirm() {
        char[] p = txtPasswordConfirm.getPassword();
        return new String(p);
    }
}

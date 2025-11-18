package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignUpHeaderPanel extends JPanel {

    public SignUpHeaderPanel() {
        JPanel signUpHeaderPanel = new JPanel();
        signUpHeaderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JLabel lblTitle = new JLabel("회원가입");
        lblTitle.setFont(UIFont.HEADER);
        signUpHeaderPanel.add(lblTitle);

        add(signUpHeaderPanel);
    }
}

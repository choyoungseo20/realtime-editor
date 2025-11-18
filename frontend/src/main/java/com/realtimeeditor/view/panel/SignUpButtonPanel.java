package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SignUpButtonPanel extends JPanel {

    private final JButton cancelButton;
    private final JButton signUpButton;

    public SignUpButtonPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        cancelButton = new JButton("취소");
        cancelButton.setFont(UIFont.EXPLAIN);

        signUpButton = new JButton("회원가입");
        signUpButton.setFont(UIFont.EXPLAIN);

        add(cancelButton);
        add(signUpButton);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public void setCancelActionListener(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    public void setSignUpActionListener(ActionListener actionListener) {
        signUpButton.addActionListener(actionListener);
    }
}

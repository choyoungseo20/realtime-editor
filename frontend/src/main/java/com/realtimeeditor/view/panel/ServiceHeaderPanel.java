package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServiceHeaderPanel extends JPanel {

    public ServiceHeaderPanel() {
        JPanel serviceHeaderPanel = new JPanel();
        serviceHeaderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JLabel lblTitle = new JLabel("Realtime Editor");
        lblTitle.setFont(UIFont.HEADER);
        serviceHeaderPanel.add(lblTitle);

        add(serviceHeaderPanel);
    }
}

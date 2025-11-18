package com.realtimeeditor.view.panel;

import com.realtimeeditor.view.ui.UIFont;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServiceNamePanel extends JPanel {

    public ServiceNamePanel() {
        JPanel serviceNamePanel = new JPanel();
        serviceNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JLabel lblTitle = new JLabel("Realtime Editor");
        lblTitle.setFont(UIFont.HEADER);
        serviceNamePanel.add(lblTitle);

        add(serviceNamePanel);
    }
}

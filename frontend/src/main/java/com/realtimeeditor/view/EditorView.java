package com.realtimeeditor.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditorView extends JFrame {

    public JTextArea textArea = new JTextArea();

    public EditorView() {
        setTitle("Realtime Editor");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JScrollPane(textArea));
    }
}

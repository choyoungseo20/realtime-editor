package com.realtimeeditor.controller;

import com.realtimeeditor.crdt.CrdtEngine;
import com.realtimeeditor.crdt.CrdtOperation;
import com.realtimeeditor.view.EditorView;
import com.realtimeeditor.websocket.WebSocketManager;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditorController  {

    private final EditorView editorView;
    private final CrdtEngine crdtEngine;

    private boolean localChange = false;

    public EditorController(EditorView editorView, CrdtEngine crdtEngine) {
        this.editorView = editorView;
        this.crdtEngine = crdtEngine;

        connectWebSocket();
        handleLocalInput();

        editorView.setVisible(true);
    }

    public void applyRemoteOperation(CrdtOperation operation) {
        crdtEngine.applyRemote(operation);

        SwingUtilities.invokeLater(() -> {
            try {
                setLocalChange(true);
                editorView.textArea.setText(crdtEngine.getDocumentFileText());
            } finally {
                setLocalChange(false);
            }
        });
    }

    private void setLocalChange(boolean localChange) {
        this.localChange = localChange;
    }

    private boolean isLocalChange() {
        return localChange;
    }

    private void connectWebSocket() {
        try {
            WebSocketManager.connect(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLocalInput() {
        editorView.textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isLocalChange()) return;

                try {
                    int offset = e.getOffset();
                    int length = e.getLength();
                    String insertText = editorView.textArea.getDocument().getText(offset, length);

                    for (int i = 0; i < insertText.length(); i++) {
                        char c = insertText.charAt(i);
                        CrdtOperation operation = crdtEngine.localInsert(offset + i, c);
                        WebSocketManager.sendMessage(operation);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isLocalChange()) return;

                int offset = e.getOffset();
                int length = e.getLength();

                for (int i = 0; i < length; i++) {
                    CrdtOperation operation = crdtEngine.localDelete(offset);
                    WebSocketManager.sendMessage(operation);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
}
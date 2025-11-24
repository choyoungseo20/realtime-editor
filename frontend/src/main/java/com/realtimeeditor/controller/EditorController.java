package com.realtimeeditor.controller;

import com.realtimeeditor.crdt.CrdtElement;
import com.realtimeeditor.crdt.CrdtEngine;
import com.realtimeeditor.crdt.CrdtOperation;
import com.realtimeeditor.view.EditorView;
import com.realtimeeditor.websocket.WebSocketManager;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
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
        startAutoTyper();

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

                    String previousId = (offset == 0) ? null : crdtEngine.getVisibleElements().get(offset - 1).getId();

                    for (int i = 0; i < insertText.length(); i++) {
                        char c = insertText.charAt(i);
                        CrdtOperation operation = crdtEngine.localInsert(previousId, c);
                        previousId = operation.crdtElement.getId();
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

                List<CrdtElement> toDelete = crdtEngine.getVisibleElements().subList(offset, offset + length);

                for (CrdtElement element : toDelete) {
                    CrdtOperation operation = crdtEngine.localDelete(element.getId());
                    WebSocketManager.sendMessage(operation);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private void startAutoTyper() {
        Timer timer = new javax.swing.Timer(5000, e -> {
            try {
                String insertText = "Alice\n";
                String previousId = crdtEngine.getVisibleElements().getLast().getId();

                for (int i = 0; i < insertText.length(); i++) {
                    char c = insertText.charAt(i);
                    CrdtOperation operation = crdtEngine.localInsert(previousId, c);
                    previousId = operation.crdtElement.getId();
                    WebSocketManager.sendMessage(operation);
                }

                try {
                    setLocalChange(true);
                    editorView.textArea.setText(crdtEngine.getDocumentFileText());
                } finally {
                    setLocalChange(false);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        timer.start();
    }
}
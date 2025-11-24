package com.realtimeeditor.controller;

import com.realtimeeditor.crdt.CrdtEngine;
import com.realtimeeditor.crdt.CrdtOperation;
import com.realtimeeditor.view.EditorView;
import com.realtimeeditor.websocket.WebSocketManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

public class EditorController  {

    private final EditorView editorView;
    private final CrdtEngine crdtEngine;

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
            editorView.textArea.setText(crdtEngine.getDocumentFileText());
        });
    }

    private void connectWebSocket() {
        try {
            WebSocketManager.connect(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLocalInput() {
        editorView.textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
                    return;
                }

                int index = editorView.textArea.getCaretPosition();
                char c = e.getKeyChar();

                CrdtOperation operation = crdtEngine.localInsert(index, c);
                WebSocketManager.sendMessage(operation);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int caretPosition = editorView.textArea.getCaretPosition();

                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    int targetIndex = caretPosition - 1;
                    if (targetIndex >= 0) {
                        CrdtOperation operation = crdtEngine.localDelete(targetIndex);
                        WebSocketManager.sendMessage(operation);
                    }
                }

                if (e.getKeyChar() == KeyEvent.VK_DELETE) {
                    int targetIndex = caretPosition;
                    if (targetIndex < crdtEngine.length()) {
                        CrdtOperation operation = crdtEngine.localDelete(targetIndex);
                        WebSocketManager.sendMessage(operation);
                    }
                }
            }
        });
    }
}

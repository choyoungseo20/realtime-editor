package com.realtimeeditor.websocket;

import com.google.gson.Gson;
import com.realtimeeditor.controller.EditorController;
import com.realtimeeditor.crdt.CrdtOperation;
import java.net.URI;

public class WebSocketManager {

    private static final Gson gson = new Gson();
    private static EditorWebSocketClient webSocketClient;
    private static EditorController editorController;

    public static void connect(EditorController controller) throws Exception {
        if (webSocketClient == null) {
            editorController = controller;
            webSocketClient = new EditorWebSocketClient(new URI("ws://localhost:8080/ws"));
            webSocketClient.connect();
        }
    }

    public static void sendMessage(Object object) {
        if (isConnected()) {
            String json = gson.toJson(object);
            webSocketClient.send(json);
        }
    }

    public static void handleReceiveMessage(String message) {
        if (isConnected()) {
            CrdtOperation operation = gson.fromJson(message, CrdtOperation.class);
            editorController.applyRemoteOperation(operation);
        }
    }

    public static void close() {
        if (isConnected()) {
            webSocketClient.close();
        }
    }

    public static boolean isConnected() {
        return webSocketClient != null && webSocketClient.isOpen();
    }
}

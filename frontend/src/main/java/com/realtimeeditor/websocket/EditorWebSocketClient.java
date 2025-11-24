package com.realtimeeditor.websocket;

import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class EditorWebSocketClient extends WebSocketClient {

    public EditorWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("connected");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("message: " + message);
        WebSocketManager.handleReceiveMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("disconnected: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}

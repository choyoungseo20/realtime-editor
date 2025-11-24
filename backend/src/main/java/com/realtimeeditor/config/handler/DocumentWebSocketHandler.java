package com.realtimeeditor.config.handler;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class DocumentWebSocketHandler extends TextWebSocketHandler {

    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        log.info("connected: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        broadcastToOtherSessions(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
        log.info("disconnected: {}", session.getId());
    }

    private void broadcastToOtherSessions(WebSocketSession session, TextMessage message)  {
        sessions.forEach((key, value) -> {
            if (isSender(session, key)) {
                return;
            }

            try {
                value.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean isSender(WebSocketSession sender, String key) {
        return key.equals(sender.getId());
    }
}

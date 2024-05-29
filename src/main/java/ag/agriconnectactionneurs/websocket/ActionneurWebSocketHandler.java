package ag.agriconnectactionneurs.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ActionneurWebSocketHandler extends TextWebSocketHandler {

    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Ajouter la session à la map avec un ID unique)
        Long actionneurId = (Long) session.getAttributes().get("actionneurId");
        sessions.put(actionneurId, session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Traitement du message entrant
    }

    public void notifyClient(Long actionneurId, String message) throws Exception {
        WebSocketSession session = sessions.get(actionneurId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    public void removeSession(Long actionneurId) {
        sessions.remove(actionneurId);
    }
}

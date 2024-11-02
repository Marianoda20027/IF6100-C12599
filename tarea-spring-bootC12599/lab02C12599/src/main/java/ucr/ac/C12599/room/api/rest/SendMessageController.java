package ucr.ac.C12599.room.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.C12599.room.handlers.commands.impl.SendMessageHandler;
import ucr.ac.C12599.room.jpa.entities.MessageEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/C12599/room")
public class SendMessageController {

    private final SendMessageHandler sendMessageHandler;

    @Autowired
    public SendMessageController(SendMessageHandler sendMessageHandler) {
        this.sendMessageHandler = sendMessageHandler;
    }

    @PostMapping("/message")
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestBody Map<String, String> payload) {
        String roomId = payload.get("id");
        String alias = payload.get("alias");
        String message = payload.get("message");
        MessageEntity msg = sendMessageHandler.handle(roomId, alias, message);
        if (msg == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", msg.getId());
        response.put("createdOn", msg.getCreatedOn().toString());
        response.put("message", msg.getMessage());
        return ResponseEntity.ok(response);
    }
}


package ucr.ac.C12599.room.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucr.ac.C12599.room.handlers.commands.impl.CreateRoomHandler;

import java.util.Map;

@RestController
@RequestMapping("/api/C12599/room")
public class CreateRoomController {

    private final CreateRoomHandler createRoomHandler;

    @Autowired
    public CreateRoomController(CreateRoomHandler createRoomHandler) {
        this.createRoomHandler = createRoomHandler;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRoom(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        String createdBy = payload.get("createdBy");
        String identifier = createRoomHandler.handle(name, createdBy);

        if (identifier == null) {
            // Return a BAD_REQUEST response with a null body if the creation fails
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(identifier);
    }
}

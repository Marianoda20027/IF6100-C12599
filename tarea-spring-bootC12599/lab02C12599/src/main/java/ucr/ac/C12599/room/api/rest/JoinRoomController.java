package ucr.ac.C12599.room.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.C12599.room.handlers.commands.impl.JoinRoomHandler;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.entities.UserEntity;
import ucr.ac.C12599.room.jpa.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/C12599/room")
public class JoinRoomController {

    private final JoinRoomHandler joinRoomHandler;
    private final UserRepository userRepository;

    @Autowired
    public JoinRoomController(JoinRoomHandler joinRoomHandler, UserRepository userRepository) {
        this.joinRoomHandler = joinRoomHandler;
        this.userRepository = userRepository;
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> joinRoom(@RequestBody Map<String, String> payload) {
        String roomId = payload.get("id");
        String alias = payload.get("alias");

        RoomEntity room = joinRoomHandler.handle(roomId, alias);

        if (room == null) {
            return ResponseEntity.ok(null);
        }

        // Obtener la lista de usuarios desde la base de datos
        List<UserEntity> users = userRepository.findByRoom(room);

        // Construir la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("id", room.getRoomIdentifier());
        response.put("name", room.getName());
        response.put("users", users.stream().map(UserEntity::getAlias).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }
}

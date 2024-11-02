package ucr.ac.C12599.room.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.C12599.room.handlers.queries.impl.ReadMessagesHandler;
import ucr.ac.C12599.room.jpa.entities.MessageEntity;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.entities.RoomMessagesResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/C12599/room")
public class ReadMessagesController {

    private final ReadMessagesHandler readMessagesHandler;

    public ReadMessagesController(ReadMessagesHandler readMessagesHandler) {
        this.readMessagesHandler = readMessagesHandler;
    }

    @GetMapping("/messages")
    public ResponseEntity<RoomMessagesResponse> getMessages(@RequestParam String roomId) {
        List<MessageEntity> messages = readMessagesHandler.getMessages(roomId);

        if (messages == null || messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Obtenemos la primera entidad de mensaje para obtener la información de la sala
        RoomEntity room = messages.get(0).getRoom();

        // Crear el DTO de respuesta con los datos de la sala
        RoomMessagesResponse response = new RoomMessagesResponse();
        response.setId(room.getRoomIdentifier());
        response.setName(room.getName());

        // Convertir los mensajes a la estructura DTO
        List<RoomMessagesResponse.MessageDTO> messageDTOs = messages.stream().map(message -> {
            RoomMessagesResponse.MessageDTO dto = new RoomMessagesResponse.MessageDTO();
            dto.setAlias(message.getUser().getAlias());
            dto.setMensaje(message.getMessage());
            dto.setCreatedOn(message.getCreatedOn());
            return dto;
        }).collect(Collectors.toList());

        // Establecemos la lista de mensajes en la respuesta
        response.setMessages(messageDTOs);

        // Devolver la respuesta formateada
        return ResponseEntity.ok(response);
    }
}

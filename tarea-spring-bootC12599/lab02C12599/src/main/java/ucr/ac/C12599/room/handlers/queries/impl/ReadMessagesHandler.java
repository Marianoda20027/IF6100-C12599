package ucr.ac.C12599.room.handlers.queries.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ucr.ac.C12599.room.jpa.entities.MessageEntity;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.repositories.MessageRepository;
import ucr.ac.C12599.room.jpa.repositories.RoomRepository;
import ucr.ac.C12599.room.api.exceptions.ValidationReadMessage;

import java.util.List;

@Component
public class ReadMessagesHandler {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ReadMessagesHandler(RoomRepository roomRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.messageRepository = messageRepository;
    }

    public List<MessageEntity> getMessages(String roomId) {
        RoomEntity room = ValidationReadMessage.validateRoom(roomId, roomRepository);
        if (room == null) {
            return null; // Return null if room is invalid
        }

        return messageRepository.findByRoomOrderByCreatedOnAsc(room);
    }
}


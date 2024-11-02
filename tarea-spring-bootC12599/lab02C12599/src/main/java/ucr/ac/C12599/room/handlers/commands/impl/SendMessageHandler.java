package ucr.ac.C12599.room.handlers.commands.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ucr.ac.C12599.room.api.exceptions.ValidationMessage;
import ucr.ac.C12599.room.handlers.commands.ISendMessageHandler;
import ucr.ac.C12599.room.jpa.entities.MessageEntity;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.entities.UserEntity;
import ucr.ac.C12599.room.jpa.repositories.MessageRepository;
import ucr.ac.C12599.room.jpa.repositories.RoomRepository;
import ucr.ac.C12599.room.jpa.repositories.UserRepository;
import java.time.LocalDateTime;

@Component
public class SendMessageHandler implements ISendMessageHandler {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public SendMessageHandler(RoomRepository roomRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageEntity handle(String roomId, String alias, String message) {
        RoomEntity room = ValidationMessage.validateRoom(roomId, roomRepository);
        if (room == null || !ValidationMessage.isMessageValid(message)) {
            return null; // Return null if room is invalid or message is empty
        }

        UserEntity user = ValidationMessage.validateUser(alias, room, userRepository);
        if (user == null) {
            return null; // Return null if user is invalid
        }

        MessageEntity msg = new MessageEntity();
        msg.setMessage(message);
        msg.setCreatedOn(LocalDateTime.now());
        msg.setUser(user);
        msg.setRoom(room);

        return messageRepository.save(msg);
    }
}

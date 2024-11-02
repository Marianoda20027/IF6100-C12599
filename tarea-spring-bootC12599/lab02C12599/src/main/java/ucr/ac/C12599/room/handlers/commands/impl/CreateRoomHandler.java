package ucr.ac.C12599.room.handlers.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ucr.ac.C12599.room.api.exceptions.ValidationRoom;
import ucr.ac.C12599.room.handlers.commands.ICreateRoomHandler;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.entities.UserEntity;
import ucr.ac.C12599.room.jpa.repositories.RoomRepository;
import ucr.ac.C12599.room.jpa.repositories.UserRepository;


import java.util.UUID;

@Component
public class CreateRoomHandler implements ICreateRoomHandler {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreateRoomHandler(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String handle(String name, String createdBy) {
        if (!ValidationRoom.isValidRoomData(name, createdBy)) {
            return null; // Invalid room data
        }

        String identifier = UUID.randomUUID().toString();
        RoomEntity room = new RoomEntity();
        room.setName(name);
        room.setRoomIdentifier(identifier);

        roomRepository.save(room);

        UserEntity user = new UserEntity();
        user.setAlias(createdBy);
        user.setRoom(room);

        userRepository.save(user);

        return identifier;
    }
}

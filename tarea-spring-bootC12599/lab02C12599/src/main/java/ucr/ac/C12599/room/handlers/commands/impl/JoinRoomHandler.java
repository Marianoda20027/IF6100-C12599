package ucr.ac.C12599.room.handlers.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ucr.ac.C12599.room.api.exceptions.ValidationJoin;
import ucr.ac.C12599.room.handlers.commands.IJoinRoomHandler;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.entities.UserEntity;
import ucr.ac.C12599.room.jpa.repositories.RoomRepository;
import ucr.ac.C12599.room.jpa.repositories.UserRepository;

@Component
public class JoinRoomHandler implements IJoinRoomHandler {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Autowired
    public JoinRoomHandler(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RoomEntity handle(String roomId, String alias) {
        RoomEntity room = ValidationJoin.validateRoom(roomId, roomRepository);
        if (room == null || !ValidationJoin.isAliasValid(alias, room, userRepository)) {
            return null; // Return null if room is invalid or alias already exists
        }

        UserEntity user = new UserEntity();
        user.setAlias(alias);
        user.setRoom(room);

        userRepository.save(user);

        return room;
    }
}

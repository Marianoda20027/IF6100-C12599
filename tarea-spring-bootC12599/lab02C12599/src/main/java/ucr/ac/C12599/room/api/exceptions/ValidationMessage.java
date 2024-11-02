package ucr.ac.C12599.room.api.exceptions;


import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.entities.UserEntity;
import ucr.ac.C12599.room.jpa.repositories.RoomRepository;
import ucr.ac.C12599.room.jpa.repositories.UserRepository;

import java.util.Optional;

public class ValidationMessage {

    public static RoomEntity validateRoom(String roomId, RoomRepository roomRepository) {
        if (roomId == null || roomId.trim().isEmpty()) {
            return null; // Invalid roomId
        }
        Optional<RoomEntity> roomOpt = roomRepository.findByRoomIdentifier(roomId);
        return roomOpt.orElse(null); // Return null if room not found
    }

    public static UserEntity validateUser(String alias, RoomEntity room, UserRepository userRepository) {
        if (alias == null || alias.trim().isEmpty()) {
            return null; // Invalid alias
        }
        Optional<UserEntity> userOpt = userRepository.findByAliasAndRoom(alias, room);
        return userOpt.orElse(null); // Return null if user not found
    }

    public static boolean isMessageValid(String message) {
        return message != null && !message.trim().isEmpty();
    }
}

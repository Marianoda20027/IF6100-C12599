package ucr.ac.C12599.room.api.exceptions;

import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import ucr.ac.C12599.room.jpa.repositories.RoomRepository;
import ucr.ac.C12599.room.jpa.repositories.UserRepository;

import java.util.Optional;

public class ValidationJoin {
    public static RoomEntity validateRoom(String roomId, RoomRepository roomRepository) {
        if (roomId == null || roomId.trim().isEmpty()) {
            return null; // Invalid roomId
        }
        Optional<RoomEntity> roomOpt = roomRepository.findByRoomIdentifier(roomId);
        return roomOpt.orElse(null); // Return null if room not found
    }

    public static boolean isAliasValid(String alias, RoomEntity room, UserRepository userRepository) {
        if (alias == null || alias.trim().isEmpty()) {
            return false; // Invalid alias
        }
        return !userRepository.findByAliasAndRoom(alias, room).isPresent(); // Return true if alias does not exist in the room
    }
}

package ucr.ac.C12599.room.handlers.commands;

import ucr.ac.C12599.room.jpa.entities.RoomEntity;

public interface IJoinRoomHandler {
    RoomEntity handle(String roomId, String alias);
}

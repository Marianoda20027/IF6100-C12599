package ucr.ac.C12599.room.handlers.queries;

import ucr.ac.C12599.room.jpa.entities.RoomEntity;

public interface IReadMessagesHandler {
    RoomEntity handle(String roomId);
}

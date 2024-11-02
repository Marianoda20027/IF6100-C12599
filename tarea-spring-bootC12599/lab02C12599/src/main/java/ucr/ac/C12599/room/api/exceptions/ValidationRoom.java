package ucr.ac.C12599.room.api.exceptions;

public class ValidationRoom {

    public static boolean isValidRoomData(String name, String createdBy) {
        return name != null && !name.trim().isEmpty() && createdBy != null && !createdBy.trim().isEmpty();
    }
}

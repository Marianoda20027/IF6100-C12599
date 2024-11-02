package ucr.ac.C12599.room.handlers.commands;

import ucr.ac.C12599.room.jpa.entities.MessageEntity;

public interface ISendMessageHandler {
    MessageEntity handle(String roomId, String alias, String message);
}

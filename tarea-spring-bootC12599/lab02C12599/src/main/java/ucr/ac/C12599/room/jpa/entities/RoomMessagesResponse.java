package ucr.ac.C12599.room.jpa.entities;

import java.time.LocalDateTime;
import java.util.List;

public class RoomMessagesResponse {

    private String id;
    private String name;
    private List<MessageDTO> messages;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    // Clase interna para representar los mensajes individuales
    public static class MessageDTO {
        private String alias;
        private String mensaje;
        private LocalDateTime createdOn;

        // Getters y Setters
        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public LocalDateTime getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
        }
    }
}

CREATE TABLE MESSAGE_C12599 (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                message TEXT NOT NULL,
                                created_on TIMESTAMP NOT NULL,
                                user_id BIGINT NOT NULL,
                                room_id BIGINT NOT NULL,
                                CONSTRAINT fk_messages_user FOREIGN KEY (user_id) REFERENCES USER_C12599(id),
                                CONSTRAINT fk_messages_room FOREIGN KEY (room_id) REFERENCES ROOM_C12599(id)
);
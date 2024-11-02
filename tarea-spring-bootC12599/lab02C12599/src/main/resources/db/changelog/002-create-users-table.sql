CREATE TABLE USER_C12599 (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             alias VARCHAR(255) NOT NULL,
                             room_id BIGINT NOT NULL,
                             FOREIGN KEY (room_id) REFERENCES ROOM_C12599(id)
);

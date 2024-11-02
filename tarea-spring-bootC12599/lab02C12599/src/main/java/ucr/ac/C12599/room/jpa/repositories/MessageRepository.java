package ucr.ac.C12599.room.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ucr.ac.C12599.room.jpa.entities.MessageEntity;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query("SELECT m FROM MessageEntity m WHERE m.room = :room ORDER BY m.createdOn ASC")
    List<MessageEntity> findByRoomOrderByCreatedOnAsc(RoomEntity room);

}



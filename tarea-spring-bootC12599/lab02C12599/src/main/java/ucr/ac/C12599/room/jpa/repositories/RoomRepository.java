package ucr.ac.C12599.room.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByRoomIdentifier(String roomIdentifier);
}

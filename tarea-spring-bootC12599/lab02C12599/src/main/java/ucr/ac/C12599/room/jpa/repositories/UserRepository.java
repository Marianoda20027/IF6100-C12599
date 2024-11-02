package ucr.ac.C12599.room.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.C12599.room.jpa.entities.UserEntity;
import ucr.ac.C12599.room.jpa.entities.RoomEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Método para encontrar usuarios por sala
    List<UserEntity> findByRoom(RoomEntity room);

    // Método para encontrar un usuario por alias y sala
    Optional<UserEntity> findByAliasAndRoom(String alias, RoomEntity room);}

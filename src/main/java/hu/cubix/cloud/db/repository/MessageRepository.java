package hu.cubix.cloud.db.repository;

import hu.cubix.cloud.db.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findFirst5ByOrderByDateTimeDesc();
}

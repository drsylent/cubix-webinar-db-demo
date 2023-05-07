package hu.cubix.cloud.service;

import hu.cubix.cloud.db.entity.MessageEntity;
import hu.cubix.cloud.db.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void createNewMessage(String message) {
        log.info("Creating new message in database with message: {}", message);
        var newEntity = new MessageEntity(message, LocalDateTime.now());
        repository.save(newEntity);
    }

    public List<String> getRecentMessages() {
        log.info("Getting recent messages from database");
        return repository.findFirst5ByOrderByDateTimeDesc().stream().map(MessageEntity::getMessage).toList();
    }
}

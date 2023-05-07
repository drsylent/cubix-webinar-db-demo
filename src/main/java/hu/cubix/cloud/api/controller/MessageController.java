package hu.cubix.cloud.api.controller;

import hu.cubix.cloud.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping(value = "/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getRecentMessages() {
        log.info("Request arrived on /recent endpoint");
        List<String> recentMessages = service.getRecentMessages();
        log.info("Sending back response on /recent endpoint with data: number of recentMessages {}", recentMessages.size());
        return recentMessages;
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Object> newMessageArrived(@RequestBody String message) {
        log.info("Request arrived on /message endpoint with message: {}", message);
        service.createNewMessage(message);
        log.info("New message created on /message endpoint");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package ru.job4j.ManagingPassports.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class KafkaController {
    @Autowired
    private Producer producer;

    @GetMapping("/example")
    public String sendMsg(@PathParam("message") String message) {
        producer.sendMessage(message);
        return "OK";
    }
}

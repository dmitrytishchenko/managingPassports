package ru.job4j.ManagingPassports.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(String message) {
//        kafkaTemplate.send("msg", message);
        return "Message send to listener";
    }
}

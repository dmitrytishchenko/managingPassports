package ru.job4j.ManagingPassports.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.job4j.ManagingPassports.model.Passport;

@Service
public class ConsumerService {

    @KafkaListener(topics = "passport")
    @Payload(required = false)
    public void printMessage(ConsumerRecord<String, Passport> input) {
        System.out.println(input.value());
    }
}

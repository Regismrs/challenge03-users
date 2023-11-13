package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.dtos.Notification;
import com.compassuol.sp.challenge.msuser.domain.enums.EventsEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    private final String EXCHANGE = "direct-exchange";
    private final String QUEUE = "notifications";
    private final RabbitTemplate rabbitTemplate;

    public PublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String email, EventsEnum event) {

        try {
            Notification msg = new Notification(email, event);
            rabbitTemplate.convertAndSend(
                    QUEUE,
                    new ObjectMapper().writeValueAsString(msg));
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}

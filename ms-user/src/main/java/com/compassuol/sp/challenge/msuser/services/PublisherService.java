package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.dto.Notification;
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

    public void send(String email, String event) {
        rabbitTemplate.convertAndSend(EXCHANGE, QUEUE, new Notification(email, event));
    }
}

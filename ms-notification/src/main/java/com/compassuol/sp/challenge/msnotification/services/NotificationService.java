package com.compassuol.sp.challenge.msnotification.services;

import com.compassuol.sp.challenge.msnotification.domain.entities.Notification;
import com.compassuol.sp.challenge.msnotification.repositories.NotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository repository;
    private final ObjectMapper objectMapper;

    public NotificationService(NotificationRepository repository,
                               ObjectMapper objectMapper) {

        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "notifications")
    public void onMessageReceived(String message) {
        System.out.println("Received [" + message + "]");
        saveNotification(message);
    }

    private void saveNotification(String message) {
        try {
            Notification notification = objectMapper.readValue(message, Notification.class);
            repository.insert(notification);

        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }

    }
}

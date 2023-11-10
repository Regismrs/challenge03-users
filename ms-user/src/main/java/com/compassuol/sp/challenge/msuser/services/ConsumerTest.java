package com.compassuol.sp.challenge.msuser.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerTest {
    @RabbitListener(queues = "notifications")
    public void onMessageReceived(String message) {
        System.out.println("Received [" + message + "]");

        // TODO send an email using the data
    }
}

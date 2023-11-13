package com.compassuol.sp.challenge.msnotification.repositories;

import com.compassuol.sp.challenge.msnotification.domain.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}

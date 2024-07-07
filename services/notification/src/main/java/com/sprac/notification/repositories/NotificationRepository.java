package com.sprac.notification.repositories;

import com.sprac.notification.kafka.payment.PaymentConfirmation;
import com.sprac.notification.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}

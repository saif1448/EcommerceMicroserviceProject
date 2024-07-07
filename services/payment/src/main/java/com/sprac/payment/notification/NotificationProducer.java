package com.sprac.payment.notification;

import com.sprac.payment.dtos.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    Logger logger = LoggerFactory.getLogger(NotificationProducer.class);

    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification (PaymentNotificationRequest request){
        logger.info("Sending notification <{}>", request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);
    }

}

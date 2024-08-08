package com.sprac.notification.kafka;


import com.sprac.notification.kafka.order.OrderConfirmation;
import com.sprac.notification.kafka.payment.PaymentConfirmation;
import com.sprac.notification.models.Notification;
import com.sprac.notification.models.NotificationType;
import com.sprac.notification.repositories.NotificationRepository;
import com.sprac.notification.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {

        logger.info("Consuming the message from payment-topic Topic:: " + paymentConfirmation);
//        repository.save(Notification.builder()
//                .type(NotificationType.PAYMENT_CONFIRMATION)
//                .notificationDate(LocalDateTime.now())
//                .paymentConfirmation(paymentConfirmation)
//                .build());
        Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();

        //todo:: send email
        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {

        logger.info("Consuming the message from order-topic Topic:: " + orderConfirmation);
//        repository.save(Notification.builder()
//                .type(NotificationType.ORDER_CONFIRMATION)
//                .notificationDate(LocalDateTime.now())
//                .orderConfirmation(orderConfirmation)
//                .build());
        Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();

        //todo:: send email
        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );


    }

}

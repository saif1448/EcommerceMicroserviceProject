package com.sprac.payment.services;

import com.sprac.payment.dtos.PaymentNotificationRequest;
import com.sprac.payment.dtos.PaymentRequest;
import com.sprac.payment.notification.NotificationProducer;
import com.sprac.payment.repositories.PaymentRepository;
import com.sprac.payment.utils.ClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final ClassMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.fromPaymentRequestToPaymentEntity(request));

        //Notification sending
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return  payment.getId();
    }
}

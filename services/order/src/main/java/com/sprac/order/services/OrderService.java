package com.sprac.order.services;

import com.sprac.order.dtos.*;
import com.sprac.order.exceptions.BusinessException;
import com.sprac.order.kafka.OrderProducer;
import com.sprac.order.repositories.OrderRepository;
import com.sprac.order.services.customer.CustomerClient;
import com.sprac.order.services.payment.PaymentClient;
import com.sprac.order.utils.ClassMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final CustomerClient customerCLient;
    private final ProductClientService productClient;
    private final ClassMapper mapper;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;

    private final OrderProducer orderProducer;

    public int createOrder(OrderRequest request) {

        //check the customer exists of not --> OpenFeign
        var customer = this.customerCLient.findCustomerById(request.customerId()).orElseThrow(
                () -> new BusinessException("Cannot create order :: No Customer was not found with ID :: " + request.customerId())
        );

        //purchase the products ---> from product microservice
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.repository.save(mapper.orderRequesrtToOrderEntityMapper(request));

        //persist order lines
        for(PurchaseRequest purchaseRequest : request.products()){
            OrderLineRequest orderLineRequest = new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity());
            orderLineService.saveOrderLine(orderLineRequest);
        }

        //todo:: start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //todo:: send the order confirmation ---> notification microservice (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrderEntityToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::fromOrderEntityToOrderResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("No order found with the provided id" + id)
                );
    }
}

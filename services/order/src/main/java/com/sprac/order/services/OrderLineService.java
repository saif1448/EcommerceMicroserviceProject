package com.sprac.order.services;

import com.sprac.order.dtos.OrderLineRequest;
import com.sprac.order.dtos.OrderLineResponse;
import com.sprac.order.models.OrderLine;
import com.sprac.order.repositories.OrderLinerRepository;
import com.sprac.order.utils.ClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLinerRepository repository;
    private final ClassMapper mapper;

    public OrderLine saveOrderLine(OrderLineRequest orderLineRequest){
        var order = mapper.orderLineRequestToOrderLineEntityMapper(orderLineRequest);
        return repository.save(order);
    }

    public List<OrderLineResponse> findByOrderId(Integer id) {

        return repository.findAllByOrderId(id)
                .stream()
                .map(mapper::fromOrderEntityToOrderLineResponse)
                .collect(Collectors.toList());
    }
}

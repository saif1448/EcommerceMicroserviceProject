package com.sprac.order.repositories;

import com.sprac.order.dtos.OrderLineResponse;
import com.sprac.order.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLinerRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer id);
}

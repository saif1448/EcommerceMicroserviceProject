package com.sprac.order.controllers;


import com.sprac.order.dtos.OrderLineResponse;
import com.sprac.order.services.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/orderline")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderLineService.findByOrderId(id));
    }
}

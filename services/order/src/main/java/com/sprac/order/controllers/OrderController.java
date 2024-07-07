package com.sprac.order.controllers;

import com.sprac.order.dtos.OrderRequest;
import com.sprac.order.dtos.OrderResponse;
import com.sprac.order.services.OrderService;
import feign.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequest request){

        return ResponseEntity.ok("Order is placed with ID::"+service.createOrder(request));

    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

}

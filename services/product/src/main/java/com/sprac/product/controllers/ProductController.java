package com.sprac.product.controllers;


import com.sprac.product.dtos.ProductPurchaseRequest;
import com.sprac.product.dtos.ProductPurchaseResponse;
import com.sprac.product.dtos.ProductRequest;
import com.sprac.product.dtos.ProductResponse;
import com.sprac.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

//    @Autowired
//    public ProductController(ProductService service) {
//        this.service = service;
//    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body(String.format("Product is created with ID::%s",service.createProduct(request)));

    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ){
            return ResponseEntity.ok(service.purchaseProducts(request));
    }

    @GetMapping("/id")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProduct(){
        return ResponseEntity.ok(service.findAllProducts());
    }

}

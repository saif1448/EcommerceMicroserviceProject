package com.sprac.customer.controllers;


import com.sprac.customer.exceptions.CustomerNotFound;
import com.sprac.customer.models.Customer;
import com.sprac.customer.models.dtos.CustomerRequest;
import com.sprac.customer.models.dtos.CustomerResponse;
import com.sprac.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.createCustomer(request));
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(
        @RequestBody @Valid CustomerRequest request
    ){
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findIndividualCustomer(@PathVariable("id") Long id){
        Customer customer = service.findByCustomerId(id).orElse(null);
        if(customer != null){
            return ResponseEntity.ok(new CustomerResponse(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getAddress()
            ));
        }
        else{
            throw new CustomerNotFound("Cannot find the customer with the provided ID::" + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        service.deleteCustomer(id);
        return ResponseEntity.ok("Customer Deleted");
    }
}

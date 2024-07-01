package com.sprac.customer.service;


import com.sprac.customer.models.Customer;
import com.sprac.customer.models.dtos.CustomerResponse;
import com.sprac.customer.models.dtos.CustomerRequest;
import com.sprac.customer.repositories.CustomerRepository;
import com.sprac.customer.exceptions.CustomerNotFound;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class CustomerService {

   private final CustomerRepository repository;

    public Customer createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();

        return repository.save(customer);
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFound(
                    format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
                ));
        mergetCustomer(customer, request);
        repository.save(customer);
    }

    private void mergetCustomer(Customer customer, CustomerRequest request) {

        if(StringUtils.isBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }

    }

    public Stream<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(e-> new CustomerResponse(e.getId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getEmail(),
                        e.getAddress()));
    }

    public Optional<Customer> findByCustomerId(Long id) {
        return repository.findById(id);
    }

    public void deleteCustomer(Long id) {
        repository.findById(id).orElseThrow(() ->
                new CustomerNotFound(
                        format("Cannot update customer:: No customer found with the provided ID:: %s",id)
                ));
        repository.deleteById(id);
    }
}

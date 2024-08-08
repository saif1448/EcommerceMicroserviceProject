package com.sprac.order.services.customer;

import com.sprac.order.dtos.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("id") long id);

}

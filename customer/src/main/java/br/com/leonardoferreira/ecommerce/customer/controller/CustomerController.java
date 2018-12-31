package br.com.leonardoferreira.ecommerce.customer.controller;

import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateCustomerRequest;
import br.com.leonardoferreira.ecommerce.customer.service.CustomerService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public HttpEntity<?> create(@Valid @RequestBody final CreateCustomerRequest createCustomerRequest) {
        Long id = customerService.create(createCustomerRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").build(id);

        return ResponseEntity.created(location).build();
    }

}

package br.com.leonardoferreira.ecommerce.customer.controller;

import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateAddressRequest;
import br.com.leonardoferreira.ecommerce.customer.service.AddressService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/customers/{customerId}/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public HttpEntity<?> create(@PathVariable final Long customerId,
                                @Valid @RequestBody final CreateAddressRequest createAddressRequest) {
        Long addressId = addressService.create(customerId, createAddressRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{addressId}")
                .build(addressId);
        return ResponseEntity.created(location).build();
    }

}

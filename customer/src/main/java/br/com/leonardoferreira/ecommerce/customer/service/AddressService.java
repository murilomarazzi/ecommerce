package br.com.leonardoferreira.ecommerce.customer.service;

import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateAddressRequest;

public interface AddressService {

    Long create(Long customerId, CreateAddressRequest createAddressRequest);

}

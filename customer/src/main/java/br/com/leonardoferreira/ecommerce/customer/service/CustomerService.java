package br.com.leonardoferreira.ecommerce.customer.service;

import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateCustomerRequest;

public interface CustomerService {

    Long create(CreateCustomerRequest createCustomerRequest);

    Customer findById(Long id);

}
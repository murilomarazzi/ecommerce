package br.com.leonardoferreira.ecommerce.customer.service.impl;

import br.com.leonardoferreira.ecommerce.customer.domain.Address;
import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateAddressRequest;
import br.com.leonardoferreira.ecommerce.customer.mapper.AddressMapper;
import br.com.leonardoferreira.ecommerce.customer.repository.AddressRepository;
import br.com.leonardoferreira.ecommerce.customer.service.AddressService;
import br.com.leonardoferreira.ecommerce.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public Long create(final Long customerId,
                       final CreateAddressRequest createAddressRequest) {
        Customer customer  = customerService.findById(customerId);

        Address address = addressMapper.createAddressToAddress(createAddressRequest, customer);
        addressRepository.save(address);

        return address.getId();
    }

}

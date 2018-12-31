package br.com.leonardoferreira.ecommerce.customer.service.impl;

import br.com.leonardoferreira.ecommerce.customer.client.OAuthClient;
import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateCustomerRequest;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateUserRequest;
import br.com.leonardoferreira.ecommerce.customer.exception.ResourceNotFoundException;
import br.com.leonardoferreira.ecommerce.customer.mapper.CustomerMapper;
import br.com.leonardoferreira.ecommerce.customer.repository.CustomerRepository;
import br.com.leonardoferreira.ecommerce.customer.service.CustomerService;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OAuthClient oAuthClient;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    @Transactional
    public Long create(final CreateCustomerRequest createCustomerRequest) {
        log.info("Method=create, createCustomerRequest={}", createCustomerRequest);

        CreateUserRequest customerToCreateUser = customerMapper.createCustomerToCreateUser(createCustomerRequest);
        HttpEntity<?> oauthResponse = oAuthClient.createUser(customerToCreateUser);
        Long userId = extractUserId(oauthResponse.getHeaders().getLocation());

        Customer customer = customerMapper.createCustomerToCustomer(createCustomerRequest, userId);
        customerRepository.save(customer);

        return customer.getId();
    }

    protected Long extractUserId(final URI location) {
        log.info("Method=extractUserId, location={}", location);

        Matcher matcher = Pattern.compile("^/users/(\\d+)$")
                .matcher(location.getPath());
        if (matcher.find()) {
            return Long.valueOf(matcher.group(1));
        }

        throw new ResourceNotFoundException("UserId Not found in URI: " + location);
    }

}

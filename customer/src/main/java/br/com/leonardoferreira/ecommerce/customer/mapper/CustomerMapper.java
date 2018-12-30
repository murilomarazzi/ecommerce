package br.com.leonardoferreira.ecommerce.customer.mapper;

import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateCustomerRequest;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "username", source = "email")
    })
    CreateUserRequest createCustomerToCreateUser(CreateCustomerRequest createCustomerRequest);

    @Mappings({
            @Mapping(target = "birthDate", dateFormat = "dd/MM/yyyy"),
            @Mapping(target = MappingConstants.ANY_UNMAPPED, ignore = true)
    })
    Customer createCustomerToCustomer(CreateCustomerRequest createCustomerRequest);

}

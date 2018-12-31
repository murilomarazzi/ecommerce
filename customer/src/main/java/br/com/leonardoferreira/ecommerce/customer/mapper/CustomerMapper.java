package br.com.leonardoferreira.ecommerce.customer.mapper;

import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateCustomerRequest;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "username", source = "email")
    })
    CreateUserRequest createCustomerToCreateUser(CreateCustomerRequest createCustomerRequest);

    @Mappings({ // @formatter:off
            @Mapping(target = "id",         ignore = true),
            @Mapping(target = "birthday",   source = "request.birthday", dateFormat = "dd/MM/yyyy"),
            @Mapping(target = "name",       source = "request.name"),
            @Mapping(target = "email",      source = "request.email"),
            @Mapping(target = "phone",      source = "request.phone"),
            @Mapping(target = "userId",     source = "userId"),
            @Mapping(target = "addresses",  ignore = true),
            @Mapping(target = "createdAt",  ignore = true),
            @Mapping(target = "updatedAt",  ignore = true)

    }) // @formatter:on
    Customer createCustomerToCustomer(CreateCustomerRequest request, Long userId);

}

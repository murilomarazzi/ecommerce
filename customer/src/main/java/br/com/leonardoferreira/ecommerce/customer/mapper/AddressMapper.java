package br.com.leonardoferreira.ecommerce.customer.mapper;

import br.com.leonardoferreira.ecommerce.customer.domain.Address;
import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateAddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mappings({ // @formatter:off
            @Mapping(target = "id",                 ignore = true),
            @Mapping(target = "city",               source = "request.city"),
            @Mapping(target = "address",            source = "request.address"),
            @Mapping(target = "neighborhood",       source = "request.neighborhood"),
            @Mapping(target = "federationUnity",    source = "request.federationUnity"),
            @Mapping(target = "zipCode",            source = "request.zipCode"),
            @Mapping(target = "customer",           source = "customer"),
            @Mapping(target = "createdAt",          ignore = true),
            @Mapping(target = "updatedAt",          ignore = true)
    }) // @formatter:on
    Address createAddressToAddress(CreateAddressRequest request, Customer customer);

}

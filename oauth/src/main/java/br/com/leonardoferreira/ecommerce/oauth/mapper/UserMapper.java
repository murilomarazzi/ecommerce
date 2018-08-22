package br.com.leonardoferreira.ecommerce.oauth.mapper;

import br.com.leonardoferreira.ecommerce.oauth.domain.User;
import br.com.leonardoferreira.ecommerce.oauth.domain.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id",          ignore = true),
            @Mapping(target = "password",    ignore = true),
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "createdAt",   ignore = true),
            @Mapping(target = "updatedAt",   ignore = true)
    })
    User createUserToUser(CreateUserRequest createUserRequest);

}

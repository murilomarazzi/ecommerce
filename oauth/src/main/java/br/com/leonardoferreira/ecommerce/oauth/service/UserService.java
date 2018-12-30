package br.com.leonardoferreira.ecommerce.oauth.service;

import br.com.leonardoferreira.ecommerce.oauth.domain.request.CreateUserRequest;
import br.com.leonardoferreira.ecommerce.oauth.domain.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Long create(CreateUserRequest createUserRequest);

    UserResponse findUserInfo();

    UserResponse findById(Long id);

}

package br.com.leonardoferreira.ecommerce.customer.client;

import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("oauth")
public interface OAuthClient {

    @PostMapping("/users")
    HttpEntity<?> createUser(@RequestBody CreateUserRequest request);

}

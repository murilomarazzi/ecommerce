package br.com.leonardoferreira.ecommerce.product.client;

import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "oauth")
public interface UserClient {

    @GetMapping("/users/me/info")
    UserInfo findByToken(@RequestHeader("Authorization") String authorization);

}

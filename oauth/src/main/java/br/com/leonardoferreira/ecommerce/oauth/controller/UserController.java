package br.com.leonardoferreira.ecommerce.oauth.controller;

import br.com.leonardoferreira.ecommerce.oauth.domain.request.CreateUserRequest;
import br.com.leonardoferreira.ecommerce.oauth.domain.response.UserInfo;
import br.com.leonardoferreira.ecommerce.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public Principal user(final Principal principal) {
        return principal;
    }

    @GetMapping("/me/info")
    public UserInfo user() {
        return userService.findUserInfo();
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody @Valid final CreateUserRequest createUserRequest) {
        Long id = userService.create(createUserRequest);
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }
}

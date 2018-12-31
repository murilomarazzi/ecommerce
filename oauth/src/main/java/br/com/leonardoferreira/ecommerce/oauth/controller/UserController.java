package br.com.leonardoferreira.ecommerce.oauth.controller;

import br.com.leonardoferreira.ecommerce.oauth.domain.request.CreateUserRequest;
import br.com.leonardoferreira.ecommerce.oauth.domain.response.UserResponse;
import br.com.leonardoferreira.ecommerce.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public Principal user(final Principal principal) {
        return principal;
    }

    @GetMapping("/{id}")
    public UserResponse findById(final @PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/me/info")
    public UserResponse user() {
        return userService.findUserInfo();
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody @Valid final CreateUserRequest createUserRequest) {
        Long id = userService.create(createUserRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").build(id);
        return ResponseEntity.created(uri).build();
    }
}

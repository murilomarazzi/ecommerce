package br.com.leonardoferreira.ecommerce.customer.domain.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;

    private String username;

    private String password;

}
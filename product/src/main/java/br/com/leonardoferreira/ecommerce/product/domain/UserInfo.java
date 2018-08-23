package br.com.leonardoferreira.ecommerce.product.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {

    private Long id;

    private String username;

    private String name;

    private List<String> authorities;

}

package br.com.leonardoferreira.ecommerce.oauth.domain.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserInfo {

    private Long id;

    private String username;

    private String name;

    private Set<String> authorities;

    private String createdAt;

    private String updatedAt;

}

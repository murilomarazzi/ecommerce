package br.com.leonardoferreira.ecommerce.oauth.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"password"})
public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 6)
    private String password;

}

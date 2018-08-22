package br.com.leonardoferreira.ecommerce.oauth.domain.request;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String matchPassword;

    @AssertTrue
    public boolean isPasswordConfirmed() {
        return password == null || password.equals(matchPassword);
    }

}

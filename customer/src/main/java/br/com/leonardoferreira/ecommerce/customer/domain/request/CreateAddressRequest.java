package br.com.leonardoferreira.ecommerce.customer.domain.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAddressRequest {

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String federationUnity;

    @NotBlank
    private String zipCode;

}

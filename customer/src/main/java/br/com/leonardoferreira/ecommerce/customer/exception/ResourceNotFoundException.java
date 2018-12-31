package br.com.leonardoferreira.ecommerce.customer.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8497301782510936522L;

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}

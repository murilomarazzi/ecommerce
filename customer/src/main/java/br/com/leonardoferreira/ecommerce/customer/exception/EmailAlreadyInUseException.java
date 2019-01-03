package br.com.leonardoferreira.ecommerce.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email already in use")
public class EmailAlreadyInUseException extends RuntimeException {

    private static final long serialVersionUID = 4990423169793761976L;
    
}

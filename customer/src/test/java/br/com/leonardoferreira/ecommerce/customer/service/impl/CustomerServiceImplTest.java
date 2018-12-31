package br.com.leonardoferreira.ecommerce.customer.service.impl;

import br.com.leonardoferreira.ecommerce.customer.exception.ResourceNotFoundException;
import java.net.URI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @ParameterizedTest(name = "url = {0}, userId = {1}")
    @CsvSource({
            "http://localhost:8080/users/123, 123",
            "https://oauth.ecommerce.com/users/321, 321",
            "/users/567, 567"
    })
    public void extractUserIdWithSuccess(final String url, final Long expectedResult) {
        Long userId = customerService.extractUserId(URI.create(url));

        Assertions.assertEquals(expectedResult, userId);
    }

    @ParameterizedTest
    @CsvSource({
            "http://localhost:8080/user/123",
            "http://localhost:8080/users/me",
            "http://localhost:8080/users/users/123",
            "https://oauth.ecommerce.com/users/me",
            "/customer/321"
    })
    public void extractUserIdWithoutSuccess(final String url) {
        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> customerService.extractUserId(URI.create(url)));

        Assertions.assertEquals("UserId Not found in URI: " + url, ex.getMessage());
    }
}

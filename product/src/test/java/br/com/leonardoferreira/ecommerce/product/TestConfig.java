package br.com.leonardoferreira.ecommerce.product;

import br.com.leonardoferreira.ecommerce.product.client.UserClient;
import com.github.javafaker.Faker;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public UserClient userClient() {
        return Mockito.mock(UserClient.class);
    }

}

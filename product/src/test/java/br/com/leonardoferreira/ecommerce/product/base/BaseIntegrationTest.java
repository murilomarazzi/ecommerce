package br.com.leonardoferreira.ecommerce.product.base;

import br.com.leonardoferreira.ecommerce.product.Application;
import br.com.leonardoferreira.ecommerce.product.TestConfig;
import br.com.leonardoferreira.ecommerce.product.client.UserClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = { Application.class, TestConfig.class },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected CleanDatabase cleanDatabase;

    @Autowired
    protected UserClient userClient;

    @LocalServerPort
    protected Integer port;

    @Before
    public void setup() {
        RestAssured.port = port;
        Mockito.reset(userClient);
        cleanDatabase.clean();
    }

}

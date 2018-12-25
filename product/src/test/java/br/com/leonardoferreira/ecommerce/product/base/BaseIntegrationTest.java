package br.com.leonardoferreira.ecommerce.product.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class BaseIntegrationTest {

    @Autowired
    protected CleanDatabase cleanDatabase;

    @LocalServerPort
    protected Integer port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        cleanDatabase.clean();
    }

}

package br.com.leonardoferreira.ecommerce.product.integration;

import br.com.leonardoferreira.ecommerce.product.base.BaseIntegrationTest;
import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.factory.ProductFactory;
import br.com.leonardoferreira.ecommerce.product.repository.ProductRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteProductIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void delete() {
        Product product = productFactory.create();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/products/{id}", product.getId())
                .then()
                    .log().all()
                    .statusCode(204);
        // @formatter:on

        long count = productRepository.count();
        Assertions.assertEquals(0, count);
    }

}

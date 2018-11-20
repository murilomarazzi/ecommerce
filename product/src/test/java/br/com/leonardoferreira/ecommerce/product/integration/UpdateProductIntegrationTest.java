package br.com.leonardoferreira.ecommerce.product.integration;

import br.com.leonardoferreira.ecommerce.product.base.BaseIntegrationTest;
import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.exception.ResourceNotFound;
import br.com.leonardoferreira.ecommerce.product.extension.WireMockExtension;
import br.com.leonardoferreira.ecommerce.product.factory.ProductFactory;
import br.com.leonardoferreira.ecommerce.product.factory.ProductRequestFactory;
import br.com.leonardoferreira.ecommerce.product.repository.ProductRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, WireMockExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateProductIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductRequestFactory productRequestFactory;

    @Test
    public void update() {
        Product oldProduct = productFactory.create();
        ProductRequest request = productRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .put("/products/{id}", oldProduct.getId())
                .then()
                    .log().all()
                    .statusCode(204);
        // @formatter:on

        Product product = productRepository.findById(oldProduct.getId())
                .orElseThrow(ResourceNotFound::new);

        Assertions.assertEquals(request.getName(), product.getName());
        Assertions.assertEquals(request.getPrice(), product.getPrice());
    }

    @Test
    public void updateWithChangeAgent() {
        Product oldProduct = productFactory.create();
        ProductRequest request = productRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("X-Token", "normalToken")
                    .body(request)
                .when()
                    .put("/products/{id}", oldProduct.getId())
                .then()
                    .log().all()
                    .statusCode(204);
        // @formatter:on

        Product product = productRepository.findById(oldProduct.getId())
                .orElseThrow(ResourceNotFound::new);

        Assertions.assertEquals(request.getName(), product.getName());
        Assertions.assertEquals(request.getPrice(), product.getPrice());
        Assertions.assertEquals("defaultUser", product.getChangeAgent());
    }
}

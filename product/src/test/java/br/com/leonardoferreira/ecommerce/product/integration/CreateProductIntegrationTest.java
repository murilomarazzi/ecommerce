package br.com.leonardoferreira.ecommerce.product.integration;

import br.com.leonardoferreira.ecommerce.product.base.BaseIntegrationTest;
import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.exception.ResourceNotFound;
import br.com.leonardoferreira.ecommerce.product.extension.WireMockExtension;
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
public class CreateProductIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRequestFactory productRequestFactory;

    @Test
    public void createProduct() {
        ProductRequest request = productRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .body(request)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/products")
                .then()
                    .log().all()
                    .statusCode(201)
                    .header("Location", "/products/1");
        // @formatter:on

        Product product = productRepository.findById(1L)
                .orElseThrow(ResourceNotFound::new);

        Assertions.assertEquals(request.getName(), product.getName());
        Assertions.assertEquals(request.getPrice(), product.getPrice());
    }

    @Test
    public void createWithChangeAgent() {
        ProductRequest request = productRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .body(request)
                    .header("X-Token", "normalToken")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/products")
                .then()
                    .log().all()
                    .statusCode(201)
                    .header("Location", "/products/1");
        // @formatter:on

        Product product = productRepository.findById(1L)
                .orElseThrow(ResourceNotFound::new);

        Assertions.assertEquals(request.getName(), product.getName());
        Assertions.assertEquals(request.getPrice(), product.getPrice());
        Assertions.assertEquals("defaultUser", product.getChangeAgent());
    }
}

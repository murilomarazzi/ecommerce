package br.com.leonardoferreira.ecommerce.product.integration;

import br.com.leonardoferreira.ecommerce.product.base.BaseIntegrationTest;
import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.factory.ProductFactory;
import br.com.leonardoferreira.ecommerce.product.matcher.IdMatchers;
import io.restassured.RestAssured;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListProductIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ProductFactory productFactory;

    @Test
    public void findAll() {
        List<Product> products = productFactory.create(5);
        Product lastProduct = products.get(4);

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/products")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("$", Matchers.hasSize(products.size()))
                    .body("[4].id", IdMatchers.is(lastProduct.getId()))
                    .body("[4].name", Matchers.is(lastProduct.getName()))
                    .body("[4].price", Matchers.is(new DecimalFormat("R$ #.00").format(lastProduct.getPrice())))
                    .body("[4].createdAt", Matchers.is(lastProduct.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))))
                    .body("[4].updatedAt", Matchers.is(lastProduct.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        // @formatter:on
    }

    @Test
    public void findById() {
        Product product = productFactory.create();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/products/{id}", product.getId())
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("id", IdMatchers.is(product.getId()))
                    .body("name", Matchers.is(product.getName()))
                    .body("price", Matchers.is(new DecimalFormat("R$ #.00").format(product.getPrice())))
                    .body("createdAt", Matchers.is(product.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))))
                    .body("updatedAt", Matchers.is(product.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        // @formatter:on
    }

}

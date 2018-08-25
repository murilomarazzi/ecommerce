package br.com.leonardoferreira.ecommerce.product.integration;

import br.com.leonardoferreira.ecommerce.product.base.BaseIntegrationTest;
import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.exception.ResourceNotFound;
import br.com.leonardoferreira.ecommerce.product.factory.ProductFactory;
import br.com.leonardoferreira.ecommerce.product.factory.ProductRequestFactory;
import br.com.leonardoferreira.ecommerce.product.matcher.IdMatchers;
import br.com.leonardoferreira.ecommerce.product.repository.ProductRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
public class ProductIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductRequestFactory productRequestFactory;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAllTest() {
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

        Assertions.assertThat(product.getName())
                .isEqualTo(request.getName());
        Assertions.assertThat(product.getPrice())
                .isEqualTo(request.getPrice());
    }

    @Test
    public void createWithChangeAgent() {
        ProductRequest request = productRequestFactory.build();
        Mockito.when(userClient.findByToken(Mockito.eq("normalToken")))
                .thenReturn(new UserInfo(1L, "username", "name", null));

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

        Assertions.assertThat(product.getName())
                .isEqualTo(request.getName());
        Assertions.assertThat(product.getPrice())
                .isEqualTo(request.getPrice());
        Assertions.assertThat(product.getChangeAgent())
                .isEqualTo("username");
    }

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
        Assertions.assertThat(count)
                .isZero();
    }

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

        Assertions.assertThat(product.getName())
                .isEqualTo(request.getName());
        Assertions.assertThat(product.getPrice())
                .isEqualTo(request.getPrice());
    }

    @Test
    public void updateWithChangeAgent() {
        Product oldProduct = productFactory.create();
        ProductRequest request = productRequestFactory.build();

        Mockito.when(userClient.findByToken(Mockito.eq("normalToken")))
                .thenReturn(new UserInfo(1L, "username", "name", null));

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

        Assertions.assertThat(product.getName())
                .isEqualTo(request.getName());
        Assertions.assertThat(product.getPrice())
                .isEqualTo(request.getPrice());
        Assertions.assertThat(product.getChangeAgent())
                .isEqualTo("username");
    }
}

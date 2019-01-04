package br.com.leonardoferreira.ecommerce.customer.integration;

import br.com.leonardoferreira.ecommerce.customer.base.BaseIntegrationTest;
import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateCustomerRequest;
import br.com.leonardoferreira.ecommerce.customer.exception.ResourceNotFoundException;
import br.com.leonardoferreira.ecommerce.customer.extension.WireMockExtension;
import br.com.leonardoferreira.ecommerce.customer.factory.CreateCustomerRequestFactory;
import br.com.leonardoferreira.ecommerce.customer.factory.CustomerFactory;
import br.com.leonardoferreira.ecommerce.customer.repository.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.format.DateTimeFormatter;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, WireMockExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCustomerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CreateCustomerRequestFactory createCustomerRequestFactory;

    @Autowired
    private CustomerFactory customerFactory;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void createCustomerWithSuccess() {
        CreateCustomerRequest request = createCustomerRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/customers")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .header("location", Matchers.containsString("/customers/1"));
        // @formatter:on

        Customer customer = customerRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("Customer content",
                () -> Assertions.assertEquals(123L, customer.getUserId().longValue()),
                () -> Assertions.assertEquals(request.getName(), customer.getName()),
                () -> Assertions.assertEquals(request.getEmail(), customer.getEmail()),
                () -> Assertions.assertEquals(request.getPhone(), customer.getPhone()),
                () -> Assertions.assertEquals(request.getBirthday(), customer.getBirthday()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    @Test
    public void createUserWithEmailAlreadyInUse() {
        Customer customer = customerFactory.create();
        CreateCustomerRequest request = createCustomerRequestFactory.build(empty -> {
            empty.setEmail(customer.getEmail());
        });

        // @formatter:off
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/customers")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("message", Matchers.is("Email already in use"));
        // @formatter:on
    }

    @Test
    public void failInValidations() {
        CreateCustomerRequest request = new CreateCustomerRequest();

        // @formatter:off
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/customers")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors.find { it.field == 'email' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'password' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'name' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'birthday' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'phone' }.defaultMessage", Matchers.is("must not be blank"))
                ;
        // @formatter:on

    }

}

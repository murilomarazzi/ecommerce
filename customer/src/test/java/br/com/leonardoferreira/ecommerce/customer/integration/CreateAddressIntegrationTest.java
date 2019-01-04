package br.com.leonardoferreira.ecommerce.customer.integration;

import br.com.leonardoferreira.ecommerce.customer.base.BaseIntegrationTest;
import br.com.leonardoferreira.ecommerce.customer.domain.Address;
import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateAddressRequest;
import br.com.leonardoferreira.ecommerce.customer.exception.ResourceNotFoundException;
import br.com.leonardoferreira.ecommerce.customer.factory.CreateAddressRequestFactory;
import br.com.leonardoferreira.ecommerce.customer.factory.CustomerFactory;
import br.com.leonardoferreira.ecommerce.customer.repository.AddressRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateAddressIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CreateAddressRequestFactory createAddressRequestFactory;

    @Autowired
    private CustomerFactory customerFactory;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void createAddressWithSuccess() {
        Customer customer = customerFactory.create();
        CreateAddressRequest request = createAddressRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/customers/{customerId}/addresses", customer.getId())
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .header("location", Matchers.containsString("/customers/1/addresses/1"));
        // @formatter:on

        Address address = addressRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);
        Assertions.assertAll("address content",
                () -> Assertions.assertEquals(request.getCity(), address.getCity()),
                () -> Assertions.assertEquals(request.getAddress(), address.getAddress()),
                () -> Assertions.assertEquals(request.getNeighborhood(), address.getNeighborhood()),
                () -> Assertions.assertEquals(request.getFederationUnity(), address.getFederationUnity()),
                () -> Assertions.assertEquals(request.getZipCode(), address.getZipCode()),
                () -> Assertions.assertEquals(customer.getId(), address.getCustomer().getId()));
    }

    @Test
    public void invalidCustomer() {
        CreateAddressRequest request = createAddressRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/customers/1/addresses")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .body("message", Matchers.is("Customer not found"));
        // @formatter:on
    }

    @Test
    public void failInValidations() {
        CreateAddressRequest request = new CreateAddressRequest();

        // @formatter:off
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/customers/1/addresses")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors.find { it.field == 'city' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'address' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'neighborhood' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'federationUnity' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'zipCode' }.defaultMessage", Matchers.is("must not be blank"));
        // @formatter:on
    }
}

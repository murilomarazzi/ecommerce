package br.com.leonardoferreira.ecommerce.customer.factory;

import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateCustomerRequest;
import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerRequestFactory extends JBacon<CreateCustomerRequest> {

    @Autowired
    private Faker faker;

    @Override
    protected CreateCustomerRequest getDefault() {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();

        createCustomerRequest.setName(faker.gameOfThrones().character());
        createCustomerRequest.setEmail(faker.internet().emailAddress());
        createCustomerRequest.setPhone(faker.phoneNumber().cellPhone());
        createCustomerRequest.setPassword(faker.internet().password());
        createCustomerRequest.setBirthday(new SimpleDateFormat("dd/MM/yyyy")
                .format(faker.date().birthday()));

        return createCustomerRequest;
    }

    @Override
    protected CreateCustomerRequest getEmpty() {
        return new CreateCustomerRequest();
    }

    @Override
    protected void persist(final CreateCustomerRequest createCustomerRequest) {
        throw new UnsupportedOperationException();
    }
}

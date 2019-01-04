package br.com.leonardoferreira.ecommerce.customer.factory;

import br.com.leonardoferreira.ecommerce.customer.domain.request.CreateAddressRequest;
import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAddressRequestFactory extends JBacon<CreateAddressRequest> {

    @Autowired
    private Faker faker;

    @Override
    protected CreateAddressRequest getDefault() {
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();

        createAddressRequest.setCity(faker.address().city());
        createAddressRequest.setAddress(faker.address().streetName());
        createAddressRequest.setNeighborhood(faker.address().streetAddress());
        createAddressRequest.setFederationUnity(faker.address().stateAbbr());
        createAddressRequest.setZipCode(faker.address().zipCode());

        return createAddressRequest;
    }

    @Override
    protected CreateAddressRequest getEmpty() {
        return new CreateAddressRequest();
    }

    @Override
    protected void persist(final CreateAddressRequest createAddressRequest) {
        throw new UnsupportedOperationException();
    }
}

package br.com.leonardoferreira.ecommerce.customer.factory;

import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import br.com.leonardoferreira.ecommerce.customer.repository.CustomerRepository;
import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory extends JBacon<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Faker faker;

    @Override
    protected Customer getDefault() {
        Customer customer = new Customer();

        customer.setName(faker.gameOfThrones().character());
        customer.setEmail(faker.internet().emailAddress());
        customer.setPhone(faker.phoneNumber().cellPhone());
        customer.setBirthday(faker.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        customer.setUserId(faker.number().numberBetween(1000L, 2000L));

        return customer;
    }

    @Override
    protected Customer getEmpty() {
        return new Customer();
    }

    @Override
    protected void persist(final Customer customer) {
        customerRepository.save(customer);
    }
}

package br.com.leonardoferreira.ecommerce.customer.repository;

import br.com.leonardoferreira.ecommerce.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    boolean existsByEmail(String email);

}

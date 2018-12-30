package br.com.leonardoferreira.ecommerce.customer.repository;

import br.com.leonardoferreira.ecommerce.customer.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}

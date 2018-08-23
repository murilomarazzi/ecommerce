package br.com.leonardoferreira.ecommerce.product.repository;

import br.com.leonardoferreira.ecommerce.product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}

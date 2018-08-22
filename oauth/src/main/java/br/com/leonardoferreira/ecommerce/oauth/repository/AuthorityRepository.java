package br.com.leonardoferreira.ecommerce.oauth.repository;

import br.com.leonardoferreira.ecommerce.oauth.domain.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}

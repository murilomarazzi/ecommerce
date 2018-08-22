package br.com.leonardoferreira.ecommerce.oauth.repository;

import br.com.leonardoferreira.ecommerce.oauth.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    User findByUsername(String username);

}

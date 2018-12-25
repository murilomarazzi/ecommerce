package br.com.leonardoferreira.ecommerce.oauth.repository;

import br.com.leonardoferreira.ecommerce.oauth.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findByUsername(String username);

}

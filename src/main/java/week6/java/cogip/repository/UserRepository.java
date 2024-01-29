package week6.java.cogip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import week6.java.cogip.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Short> {
    Optional<User> findByUsername(String username);
}

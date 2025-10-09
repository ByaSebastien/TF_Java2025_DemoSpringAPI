package be.bstorm.tf_java2025_demospringapi.dal.repositories;

import be.bstorm.tf_java2025_demospringapi.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.username ilike :login or u.email = :login")
    Optional<User> findByUsernameOrEmail(String login);
}

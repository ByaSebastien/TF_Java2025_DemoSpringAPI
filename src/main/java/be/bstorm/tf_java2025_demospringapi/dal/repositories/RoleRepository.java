package be.bstorm.tf_java2025_demospringapi.dal.repositories;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}

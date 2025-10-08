package be.bstorm.tf_java2025_demospringapi.dal.repositories;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

package be.bstorm.tf_java2025_demospringapi.bll.services;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<Book> findAll(Pageable pageable);
    Book findById(Long id);
    void Save(Book book);
    void Update(Long id, Book book);
    void Delete(Long id);
}

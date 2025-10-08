package be.bstorm.tf_java2025_demospringapi.bll.services.impls;

import be.bstorm.tf_java2025_demospringapi.bll.services.BookService;
import be.bstorm.tf_java2025_demospringapi.dal.repositories.BookRepository;
import be.bstorm.tf_java2025_demospringapi.dl.entities.Book;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public void Save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void Update(Long id, Book book) {
        Book existing = bookRepository.findById(id).orElseThrow();

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setDescription(book.getDescription());
        existing.setReleaseDate(book.getReleaseDate());

        bookRepository.save(existing);
    }

    @Override
    public void Delete(Long id) {

        if(!bookRepository.existsById(id)){
            throw new EntityNotFoundException("Book with id: " + id + " not found");
        }

        bookRepository.deleteById(id);
    }
}

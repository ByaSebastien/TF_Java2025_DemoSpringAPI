package be.bstorm.tf_java2025_demospringapi.pl.controllers;

import be.bstorm.tf_java2025_demospringapi.bll.services.BookService;
import be.bstorm.tf_java2025_demospringapi.pl.models.book.BookDetailDto;
import be.bstorm.tf_java2025_demospringapi.pl.models.book.BookForm;
import be.bstorm.tf_java2025_demospringapi.pl.models.book.BookIndexDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookIndexDto>> getBooks(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10") int size,
            @RequestParam(required = false,defaultValue = "title") String sort

    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        List<BookIndexDto> books = bookService.findAll(pageable)
                .getContent().stream()
                .map(BookIndexDto::fromEntity)
                .toList();

        return ResponseEntity.ok(books);
//        return ResponseEntity.status(HttpStatus.OK).body(books);
//        return ResponseEntity.status(200).body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDto> getBook(
            @PathVariable Long id
    ) {

        BookDetailDto book = BookDetailDto.fromEntity(bookService.findById(id));

        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Void> createBook(
            @Valid @RequestBody BookForm bookForm
    ) {

        bookService.Save(bookForm.ToEntity());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookForm bookForm
    ) {
        bookService.Update(id, bookForm.ToEntity());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long id
    ) {
        bookService.Delete(id);
        return ResponseEntity.accepted().build();
    }
}

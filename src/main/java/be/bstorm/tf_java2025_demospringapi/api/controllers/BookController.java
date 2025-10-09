package be.bstorm.tf_java2025_demospringapi.api.controllers;

import be.bstorm.tf_java2025_demospringapi.bll.services.BookService;
import be.bstorm.tf_java2025_demospringapi.api.models.book.BookDetailDto;
import be.bstorm.tf_java2025_demospringapi.api.models.book.BookForm;
import be.bstorm.tf_java2025_demospringapi.api.models.book.BookIndexDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@CrossOrigin("*")
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

//    @PreAuthorize("hasAuthority('WRITE_PERMISSION')")
    @PostMapping
    public ResponseEntity<Void> createBook(
            @Valid @RequestBody BookForm bookForm
    ) {

        Long id = bookService.Save(bookForm.ToEntity());

        UriBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}");

        URI uri = builder.build(id);

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAuthority('WRITE_PERMISSION')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookForm bookForm
    ) {
        bookService.Update(id, bookForm.ToEntity());

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long id
    ) {

        bookService.Delete(id);
        return ResponseEntity.accepted().build();
    }
}

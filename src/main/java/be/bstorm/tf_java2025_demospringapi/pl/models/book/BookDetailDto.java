package be.bstorm.tf_java2025_demospringapi.pl.models.book;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Book;

import java.time.LocalDate;

public record BookDetailDto(
        Long id,
        String title,
        String author,
        String description,
        LocalDate releaseDate
) {

    public static BookDetailDto fromEntity(Book book) {
        return new BookDetailDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getReleaseDate()
        );
    }
}

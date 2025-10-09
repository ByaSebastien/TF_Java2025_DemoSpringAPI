package be.bstorm.tf_java2025_demospringapi.api.models.book;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Book;

public record BookIndexDto(
        Long id,
        String title,
        String author
) {

    public static BookIndexDto fromEntity(Book book) {
        return new BookIndexDto(book.getId(), book.getTitle(), book.getAuthor());
    }
}

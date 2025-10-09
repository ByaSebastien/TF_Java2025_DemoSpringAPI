package be.bstorm.tf_java2025_demospringapi.api.models.book;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookForm(
        @NotBlank
        @Size(max = 100)
        String title,
        @NotBlank
        @Size(max = 100)
        String author,
        @Size(max = 500)
        String description,
        @NotNull
        LocalDate releaseDate
) {

    public Book ToEntity() {
        return new Book(
                title,
                author,
                description,
                releaseDate
        );
    }
}

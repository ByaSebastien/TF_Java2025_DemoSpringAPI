package be.bstorm.tf_java2025_demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false) @ToString
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false,length = 100)
    private String author;

    @Column(nullable = true,length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    public Book(String title, String author, String description, LocalDate releaseDate) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.releaseDate = releaseDate;
    }
}

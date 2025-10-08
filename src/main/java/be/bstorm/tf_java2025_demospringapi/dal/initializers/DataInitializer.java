package be.bstorm.tf_java2025_demospringapi.dal.initializers;

import be.bstorm.tf_java2025_demospringapi.dal.repositories.BookRepository;
import be.bstorm.tf_java2025_demospringapi.dl.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

        if(bookRepository.count() == 0) {
            List<Book> books = List.of(
                    new Book(
                            "Eloquent JavaScript",
                            "Marijn Haverbeke",
                            "Un guide moderne pour apprendre JavaScript en profondeur, couvrant la programmation fonctionnelle et orientée objet.",
                            LocalDate.of(2018, 12, 4)
                    ),
                    new Book(
                            "You Don’t Know JS Yet",
                            "Kyle Simpson",
                            "Une série approfondie expliquant les subtilités du langage JavaScript, de la portée à l’asynchrone.",
                            LocalDate.of(2020, 2, 28)
                    ),
                    new Book(
                            "HTML and CSS: Design and Build Websites",
                            "Jon Duckett",
                            "Un livre visuellement attrayant qui enseigne les bases du HTML et du CSS avec des exemples clairs et illustrés.",
                            LocalDate.of(2011, 11, 8)
                    ),
                    new Book(
                            "JavaScript: The Good Parts",
                            "Douglas Crockford",
                            "Un classique qui explore les meilleures pratiques et les parties les plus solides de JavaScript.",
                            LocalDate.of(2008, 5, 15)
                    ),
                    new Book(
                            "Learning PHP, MySQL & JavaScript",
                            "Robin Nixon",
                            "Un guide complet pour apprendre à créer des sites web dynamiques à l’aide de PHP, MySQL, JavaScript et CSS.",
                            LocalDate.of(2021, 1, 26)
                    ),
                    new Book(
                            "CSS Secrets",
                            "Lea Verou",
                            "Collection de techniques avancées et astuces CSS pour créer des interfaces élégantes et performantes.",
                            LocalDate.of(2015, 6, 15)
                    ),
                    new Book(
                            "Don’t Make Me Think",
                            "Steve Krug",
                            "Un ouvrage fondamental sur l’ergonomie web et la conception centrée sur l’utilisateur.",
                            LocalDate.of(2014, 1, 3)
                    ),
                    new Book(
                            "Learning React",
                            "Alex Banks & Eve Porcello",
                            "Introduction claire et pratique au développement avec React, JSX et les composants modernes.",
                            LocalDate.of(2020, 6, 12)
                    ),
                    new Book(
                            "Designing with Web Standards",
                            "Jeffrey Zeldman",
                            "Un guide influent sur les bonnes pratiques et les standards du développement web.",
                            LocalDate.of(2010, 9, 21)
                    ),
                    new Book(
                            "Fullstack React",
                            "Anthony Accomazzo et al.",
                            "Un livre complet pour apprendre à construire des applications modernes avec React, Redux et GraphQL.",
                            LocalDate.of(2017, 3, 15)
                    )
            );

            bookRepository.saveAll(books);
        }
    }
}

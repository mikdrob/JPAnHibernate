package com.example.demo.bootstrap;

import com.example.demo.domain.AuthorUuid;
import com.example.demo.domain.Book;
import com.example.demo.repository.AuthorUuidRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        bookRepository.deleteAll();
        authorUuidRepository.deleteAll();

        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("whatever");
        authorUuid.setLastName("whatever1");
        AuthorUuid savedAuthorUuid = authorUuidRepository.save(authorUuid);


        Book bookDDT = new Book("DDT", "123", "CodePub", null);
        Book savedDDT = bookRepository.save(bookDDT);

        Book bookSIA = new Book("SIA", "4352342", "R2X", null);
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book->{
            System.out.println("id: " + book.getId());
        });

    }
}

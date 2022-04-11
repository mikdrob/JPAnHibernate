package com.example.demo.bootstrap;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        bookRepository.deleteAll();

        Book bookDDT = new Book("DDT", "123", "CodePub");
        Book savedDDT = bookRepository.save(bookDDT);

        Book bookSIA = new Book("SIA", "4352342", "R2X");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book->{
            System.out.println("id: " + book.getId());
        });

    }
}

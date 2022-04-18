package com.example.demo.dao;

import com.example.demo.domain.Author;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

public interface AuthorDao {
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}

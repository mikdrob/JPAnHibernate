package com.example.demo.dao;

import com.example.demo.domain.Author;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

public interface AuthorDao {
    Author getById(Long id) throws SQLException;
    Author getByName(String firstName, String lastName) throws SQLException;
}

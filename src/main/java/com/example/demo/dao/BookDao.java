package com.example.demo.dao;

import com.example.demo.domain.Book;

import java.sql.SQLException;

public interface BookDao {
    Book getById(Long id) throws SQLException;
    Book findByName(String title) throws SQLException;
}

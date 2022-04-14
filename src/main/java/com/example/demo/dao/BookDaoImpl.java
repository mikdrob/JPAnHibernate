package com.example.demo.dao;

import com.example.demo.domain.Book;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookDaoImpl implements BookDao{

    private final DataSource dataSource;
    private final AuthorDao authorDao;

    public BookDaoImpl(DataSource dataSource, AuthorDao authorDao) {
        this.dataSource = dataSource;
        this.authorDao = authorDao;
    }

    @Override
    public Book getById(Long id) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book where id = ?");

            ps.setLong(1, id);

            resultSet = ps.executeQuery();

            if (resultSet.next()){
                return getBookFromRS(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, connection, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @Override
    public Book findByName(String title) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book where title = ?");

            ps.setString(1, title);

            resultSet = ps.executeQuery();

            if (resultSet.next()){
                return getBookFromRS(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, connection, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void closeAll(ResultSet resultSet, Connection connection, PreparedStatement ps) throws SQLException {
        if (resultSet != null) resultSet.close();
        if (connection != null) connection.close();
        if (ps != null) ps.close();
    }

    private Book getBookFromRS(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong(1));
        book.setIsbn(resultSet.getString(2));
        book.setTitle(resultSet.getString(3));
        book.setPublisher(resultSet.getString(4));
        book.setAuthor(authorDao.getById(resultSet.getLong(5)));
        return book;
    }
}

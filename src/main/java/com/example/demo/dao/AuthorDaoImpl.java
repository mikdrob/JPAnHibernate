package com.example.demo.dao;

import com.example.demo.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao{

    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Author getById(Long id) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM author where id = ?");

            ps.setLong(1, id);

            resultSet = ps.executeQuery();

            if (resultSet.next()){
                return getAuthorFromRS(resultSet);
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
    public Author getByName(String firstName, String lastName) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM author where first_name = ? OR last_name = ?");

            ps.setString(1, firstName);
            ps.setString(1, lastName);

            resultSet = ps.executeQuery();

            if (resultSet.next()){
                return getAuthorFromRS(resultSet);
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

    private Author getAuthorFromRS(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        return author;
    }
}

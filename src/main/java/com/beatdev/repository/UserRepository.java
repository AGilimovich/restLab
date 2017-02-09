package com.beatdev.repository;

import com.beatdev.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * User data access object class implementation.
 * This implementation using {@code JdbcTemplate} to access data in database.
 */

public class UserRepository implements AbstractUserRepository {
    private JdbcTemplate jdbcTemplate;

    private final String selectByIdQuery = "SELECT * FROM users WHERE id=?";
    private final String selectByNameQuery = "SELECT * FROM users WHERE name=?";
    private final String selectAllQuery = "SELECT * FROM users";
    private final String insertQuery = "INSERT INTO users(name, avatarURL, status, email) VALUES (?, ?, ?, ?)";

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional(readOnly = true)
    public List<User> findAll() {
        return jdbcTemplate.query(selectAllQuery,
                new UserRowMapper());
    }


    @Override
    public long saveUser(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getAvatarURL());
                ps.setString(3, user.getStatus().toString());
                ps.setString(4, user.getEmail());
                return ps;
            }
        }, holder);

        long newUserId = holder.getKey().longValue();
        user.setId(newUserId);
        return newUserId;

    }

    @Override
    public User findUserById(long id) {
        return jdbcTemplate.queryForObject(
                selectByIdQuery,
                new Object[]{id}, new UserRowMapper());
    }

    @Override
    public User findUserByName(String name) {
        return jdbcTemplate.queryForObject(
                selectByNameQuery,
                new Object[]{name}, new UserRowMapper());
    }

}

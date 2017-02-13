package com.beatdev.repository;

import com.beatdev.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Implementation of the repository class for user objects.
 * <p>This implementation is using {@code JdbcTemplate} class to access data in database.
 */
@Repository
@Profile("production")
public class JdbcTemplateUserRepository implements AbstractUserRepository {
    private JdbcTemplate jdbcTemplate;

    private final String selectByIdQuery = "SELECT * FROM users WHERE id = ?";
    private final String updateQuery = "UPDATE users SET name = ?, avatarURL = ?, status = ?, email = ? WHERE id = ?";
    private final String insertQuery = "INSERT INTO users(name, avatarURL, status, email) VALUES (?, ?, ?, ?)";

    @Autowired
    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Saves user in the repository.
     * <p>Then sets user's id according to generated  during inserting value.
     *
     * @param user to save in the repository.
     * @return id was set for user.
     */
    @Override
    public long saveUser(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getAvatarURL());
            ps.setString(3, user.getStatus().toString());
            ps.setString(4, user.getEmail());
            return ps;
        }, holder);

        long newUserId = holder.getKey().longValue();
        user.setId(newUserId);
        return newUserId;

    }

    /**
     * Searches in the repository user with provided id.
     *
     * @param id of user.
     * @return found user.
     * @throws {@code UserNotFoundException} if user with provided id was not found in the repository.
     */
    @Override
    public User findUserById(long id) throws UserNotFoundException {
        try {
            return jdbcTemplate.queryForObject(
                    selectByIdQuery,
                    new Object[]{id}, new UserRowMapper());
        } catch (Exception e) {
            throw new UserNotFoundException("No user with id: " + id);
        }
    }

    /**
     * Update user data in the database.
     *
     * @param user with new data.
     */
    @Override
    public void update(User user) {
        long id = user.getId();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(updateQuery);
            ps.setString(1, user.getName());
            ps.setString(2, user.getAvatarURL());
            ps.setString(3, user.getStatus().toString());
            ps.setString(4, user.getEmail());
            ps.setString(5, String.valueOf(user.getId()));
            return ps;
        });
    }


}

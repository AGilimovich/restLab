package com.beatdev.repository;

import com.beatdev.domain.Status;
import com.beatdev.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aleksandr on 09.02.2017.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setAvatarURL(rs.getString("avatarURL"));
        user.setStatus(Status.fromString(rs.getString("status")));
        user.setEmail(rs.getString("email"));
        return user;
    }
}

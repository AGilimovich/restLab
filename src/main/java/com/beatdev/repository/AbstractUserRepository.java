package com.beatdev.repository;

import com.beatdev.domain.User;

import java.util.List;

/**
 * Created by Aleksandr on 09.02.2017.
 */
public interface AbstractUserRepository {

    /**
     * Saves user data in repository.
     * @param user which data to be saved.
     * @return unique id assigned to user.
     */
    long saveUser(User user);

    /**
     * Searches user in repository with provided {@id}.
     * @param id of user
     * @return constructed User object.
     */
    User findUserById(long id);

    User findUserByName(String name);

    List<User> findAll();


}

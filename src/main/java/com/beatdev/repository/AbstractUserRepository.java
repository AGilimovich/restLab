package com.beatdev.repository;

import com.beatdev.domain.User;

/**
 * This interface provides list of methods for access to user repository.
 */

public interface AbstractUserRepository {

    /**
     * Method saves user data in the repository.
     *
     * @param user which data have to be saved.
     * @return unique id assigned to user.
     */
    long saveUser(User user);

    /**
     * Searches user in the repository by id.
     *
     * @param id of user.
     * @return constructed User object.
     * @throws {@code UserNotFoundException} if user with provided id was not found in the repository.
     */
    User findUserById(long id) throws UserNotFoundException;

    /**
     * Updates user with new data.
     *
     * @param user object with new data.
     */
    void update(User user);


}

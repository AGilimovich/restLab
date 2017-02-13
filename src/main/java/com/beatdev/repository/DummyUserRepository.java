package com.beatdev.repository;

import com.beatdev.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dummy implementation of the repository class for user objects.
 * It is created for development and test purposes.
 * It is not to be used in real applications.
 * All methods have artificial 5 seconds delay to simulate delays in real applications.
 */

@Repository
@Profile("dev")
public class DummyUserRepository implements AbstractUserRepository {

    /**
     * Represents the repository in this implementation.
     */
    List<User> users = Collections.synchronizedList(new ArrayList<>());

    /**
     * Adds user into list of users. Then sets user's id according to its position in list.
     * <p>Id is equal to object index in {@link users} +1. Therefore it is integer value starting from 1.
     * int value is admissible for id because id will never exceed maximum integer value during tests.
     * In real application type of id must be long.
     * <p>Synchronization guarantees that different clients will not get id of user, while it is setting.
     *
     * @param user to save.
     * @return id was set for user.
     */
    @Override
    public long saveUser(User user) {
        int id = 0;
        synchronized (users) {
            users.add(user);
            id = users.indexOf(user) + 1;
            user.setId(id);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Searches in the repository user with provided id.
     * <p>Searching implemented by retrieving element from {@link users} by it's index which is equal id-1.
     * <p>Method ensures that provided index is not out of users ArrayList bounds.
     *
     * @param id of user.
     * @return found user or null if user with provided id was not found.
     * @throws {@code UserNotFoundException} if user with provided id was not found in the repository.
     */
    @Override
    public User findUserById(long id) throws UserNotFoundException {
        if (!ensureCapacity(id - 1)) {
            throw new UserNotFoundException("No user with id: " + id);
        }
        User user = users.get((int) id - 1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * In this implementation of repository class this method has no body,
     * because instance of user object with new data is the same that stored in {@link users}.
     *
     * @param user with new data.
     */
    @Override
    public void update(User user) {

    }

    /**
     * Checks that index is not exceeding {@link users} capacity, so that IndexOutOfBoundException will not occur during execution.
     *
     * @param index checked index.
     * @return false if index is exceeding size, true if it is not.
     */
    private boolean ensureCapacity(long index) {
        if (index >= users.size()) {
            return false;
        } else return true;
    }


}

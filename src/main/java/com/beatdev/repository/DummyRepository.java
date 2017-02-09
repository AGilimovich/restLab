package com.beatdev.repository;

import com.beatdev.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dummy implementation of repository for user domain objects.
 * It is created for development and test purposes.
 * It is not to be used in real applications.
 * All methods have artificial 5 seconds delay to simulate delays in real applications.
 */

@Repository
public class DummyRepository implements AbstractUserRepository {
    List<User> users = Collections.synchronizedList(new ArrayList<>());


    /**
     * Saves user in {@code users}.
     * Then sets {@code user} {@code id} according to its index in {@code users} list.
     * Type of {@code id} is int just for test purpose. It is admissible because index will never exceed maximum integer value in this dummy implementation.
     * In real application type of {@code id} will be long.
     * Synchronization guarantees that other clients will not get {@code id} of user while it is still setting.
     *
     * @param user to save.
     * @return {@code id} was set for user.
     */
    @Override
    public long saveUser(User user) {
        int id = 0;
        synchronized (users) {
            users.add(user);
            id = users.indexOf(user);
            user.setId(id);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public User findUserById(long id) {
        User user = users.get((int) id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByName(String name) {
        User user = null;
        synchronized (users) {
            for (User u : users) {
                if (u.getName().equals(name)) {
                    user = u;
                }
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return users;
    }
}

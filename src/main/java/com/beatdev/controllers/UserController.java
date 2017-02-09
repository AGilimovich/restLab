package com.beatdev.controllers;

import com.beatdev.repository.AbstractUserRepository;
import com.beatdev.domain.Status;
import com.beatdev.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Implementation of controller for handling requests to RESTful API exposed by this application including:
 * saving user data in repository,
 * retrieving user data from repository,
 * changing status (ONLINE/OFFLINE) of user.
 */

@RestController
public class UserController {

    private AbstractUserRepository userDao;

    @Autowired
    public UserController(AbstractUserRepository userDao) {
        this.userDao = userDao;
    }

    /**
     * Handles requests for retrieving user's data from repository.
     *
     * @param id unique identifier of user
     * @return user's data.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "id") String id) {
        return userDao.findUserById(Long.valueOf(id));
    }


    /**
     * Handles requests for saving user's data in repository.
     *
     * @param name      user's name.
     * @param avatarURL user's avatar url.
     * @param email     user's email address.
     * @return unique user id which was assigned to it upon storing in repository.
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public long saveUser(@RequestParam(value = "name") String name, @RequestParam(value = "avatarURL", required = false) String avatarURL, @RequestParam(value = "email", required = false) String email) {
        User user = new User(0, name, avatarURL, Status.OFFLINE, email);
        return userDao.saveUser(user);
    }

    /**
     * Handles requests for setting new status (ONLINE/OFFLINE) to user.
     *
     * @param status new {@Status} of user.
     * @param id     user's unique id.
     * @return {@StatusResponse} object, which includes user id, previous status and newly assigned status.
     */
    @RequestMapping(value = "/status/{id}", method = RequestMethod.POST)
    public StatusResponse setStatus(@PathVariable(value = "id") long id, @RequestParam(value = "status") String status) {
        User user = userDao.findUserById(id);
        Status prevStatus = user.getStatus();
        Status newStatus = Status.fromString(status);
        user.setStatus(newStatus);
        userDao.saveUser(user);
        return new StatusResponse(user.getId(), prevStatus, newStatus);
    }


}

package com.beatdev.controllers;

import com.beatdev.domain.User;
import com.beatdev.repository.AbstractUserRepository;
import com.beatdev.repository.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.beatdev.domain.User.Status;

/**
 * Implementation of the REST controller which handles requests to RESTful services exposed by this application including:
 * <ul>
 * <li>saving user data in repository;
 * <li>retrieving user data from repository;
 * <li>changing status (ONLINE/OFFLINE) of user.
 * </ul>
 */

@RestController
public class UserController {

    private AbstractUserRepository userDao;

    @Autowired
    public UserController(AbstractUserRepository userDao) {
        this.userDao = userDao;
    }

    /**
     * Handles requests to main page.
     *
     * @return Hello message.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String doHello() {
        return "Hello. This is a test project.";
    }


    /**
     * Handles requests for retrieving user's data from the repository.
     *
     * @param id unique identifier of user.
     * @return requested user data or http 404 status if user was not found in the repository.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> doGetUser(@PathVariable(value = "id") String id) {
        User user = null;
        try {
            user = userDao.findUserById(Long.valueOf(id));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    /**
     * Handles requests for storing user's data in the repository.
     *
     * @param name      user's name.
     * @param avatarURL user's avatar url.
     * @param email     user's email address.
     * @return unique user id which was assigned to the user upon storing in the repository.
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public long doSaveUser(@RequestParam(value = "name") String name, @RequestParam(value = "avatarURL", required = false) String avatarURL, @RequestParam(value = "email", required = false) String email) {
        User user = new User(0, name, avatarURL, Status.OFFLINE, email);
        return userDao.saveUser(user);
    }

    /**
     * Handles requests for assigning new status (ONLINE/OFFLINE) to user.
     *
     * @param status new {@Status} of user.
     * @param id     user's unique id.
     * @return {@code StatusResponse} object, which includes user id, his previous and newly assigned statuses or http 404 status if user was not found in the repository.
     */
    @RequestMapping(value = "/status/{id}", method = RequestMethod.POST)
    public ResponseEntity<StatusResponse> doSetStatus(@PathVariable(value = "id") long id, @RequestParam(value = "status") String status) {
        User user = null;
        try {
            user = userDao.findUserById(id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Status prevStatus = user.getStatus();
        Status newStatus = Status.fromString(status);
        user.setStatus(newStatus);
        userDao.update(user);
        return new ResponseEntity<>(new StatusResponse(user.getId(), prevStatus, newStatus), HttpStatus.OK);

    }


}

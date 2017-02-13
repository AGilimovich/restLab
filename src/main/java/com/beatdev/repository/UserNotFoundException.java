package com.beatdev.repository;

/**
 * Class represents exception thrown when no user was found in the repository
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

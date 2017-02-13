package com.beatdev.domain;


/**
 * The class representing user entity, contains data about user.
 */

public class User {

    /**
     * The user's unique identifier.
     */
    private long id;
    /**
     * The user's name.
     */
    private String name;
    /**
     * The user's avatar URL.
     */
    private String avatarURL;
    /**
     * The user's current status (can take two values: ONLINE or OFFLINE).
     */
    private Status status;
    /**
     * The user's email address.
     */
    private String email;

    /**
     * Nested enum which contains two possible statuses of user: online and offline.
     */
    public enum Status {
        ONLINE, OFFLINE;

        /**
         * Transform string into Status object. Method is case insensitive.
         *
         * @param status String representation of status.
         * @return Status corresponding to @param.
         * @trows IllegalArgumentException if status parameter is invalid.
         */
        public static Status fromString(String status) {
            if (status.equalsIgnoreCase(Status.ONLINE.toString())) {
                return Status.ONLINE;
            } else if (status.equalsIgnoreCase(Status.OFFLINE.toString())) {
                return Status.OFFLINE;
            } else
                throw new IllegalArgumentException("No such status: " + status);

        }

    }

    public User(long id, String name, String avatarURL, Status status, String email) {
        this.id = id;
        this.name = name;
        this.avatarURL = avatarURL;
        this.status = status;
        this.email = email;
    }


    public User() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

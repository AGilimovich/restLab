package com.beatdev.domain;


/**
 * A domain object representing user.
 */


public class User {
    private long id;
    private String name;
    private String avatarURL;
    private Status status;
    private String email;


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

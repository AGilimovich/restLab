package com.beatdev.controllers;

import static com.beatdev.domain.User.Status;


/**
 * This class represents the response to requests for setting user status (OFFLINE/ONLINE).
 */

public class StatusResponse {
    /**
     * Unique identifier of user.
     */
    private long id;
    /**
     * Previous status of user.
     */
    private Status prevStatus;
    /**
     * New status of user.
     */
    private Status newStatus;

    public Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

    public Status getPrevStatus() {
        return prevStatus;
    }

    public void setPrevStatus(Status prevStatus) {
        this.prevStatus = prevStatus;
    }


    public StatusResponse(long id, Status prevStatus, Status newStatus) {
        this.id = id;
        this.prevStatus = prevStatus;
        this.newStatus = newStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

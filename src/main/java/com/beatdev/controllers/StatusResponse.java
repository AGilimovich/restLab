package com.beatdev.controllers;

import com.beatdev.domain.Status;

/**
 * Representation of response to requests for setting user status (OFFLINE/ONLINE).
 * Response contains fields:
 * {@id} of {@User},
 * {@prevStatus} previous status of user,
 * {@newStatus} new status of user
 */

public class StatusResponse {
    private long id;

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

    private Status prevStatus;
    private Status newStatus;

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

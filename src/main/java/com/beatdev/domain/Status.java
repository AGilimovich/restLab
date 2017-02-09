package com.beatdev.domain;

/**
 * Representation of two possible statuses of users:
 * ONLINE and OFFLINE.
 *
 */
public enum Status {
    ONLINE, OFFLINE;

    /**
     * Provides corresponding Status object for String.
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

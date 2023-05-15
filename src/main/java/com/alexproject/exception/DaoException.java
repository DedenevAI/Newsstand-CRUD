package com.alexproject.exception;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
    private final String uuid;

    public DaoException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public DaoException(Exception e, String message, String uuid) {
        super(message, e);
        this.uuid = uuid;
    }

    public DaoException(Exception e, String message) {
        this(e, message, null);
    }

    public DaoException(Exception e) {
        this(e, e.getMessage());
    }
}

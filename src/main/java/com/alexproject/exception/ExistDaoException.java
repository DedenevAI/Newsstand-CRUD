package com.alexproject.exception;

public class ExistDaoException extends DaoException {
    public ExistDaoException( String uuid) {
        super("Product " + uuid + " already exist", uuid);
    }
}

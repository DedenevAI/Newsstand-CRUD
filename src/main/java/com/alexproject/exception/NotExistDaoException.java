package com.alexproject.exception;

public class NotExistDaoException extends DaoException{
    public NotExistDaoException(String uuid) {
        super("Product " + uuid + " not exist", uuid);
    }
}

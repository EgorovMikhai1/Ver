package com.app.ver.exception;

public class CarsNotExistInDataBaseException extends RuntimeException {
    public CarsNotExistInDataBaseException(String message) {
        super(message);
    }
}
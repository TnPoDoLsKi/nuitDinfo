package com.example.nuitdinfo.exception;

public class BadRequestException extends Exception{
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}

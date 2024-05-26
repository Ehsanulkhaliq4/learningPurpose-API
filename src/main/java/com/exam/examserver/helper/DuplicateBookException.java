package com.exam.examserver.helper;

public class DuplicateBookException extends RuntimeException{
    public DuplicateBookException(String message) {
        super(message);
    }
}

package com.example.exp;

public class PhoneAlreadyExistsException extends Exception{
    public PhoneAlreadyExistsException(String message) {
        super(message);
    }
}

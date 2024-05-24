package com.example.accountservice.exception;

public class DuplicationException extends RuntimeException{
    public DuplicationException(){
        super("This email is already in use.");
    }
}
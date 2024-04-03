package com.example.reviewservice.excepiton;

public class InvalidData extends RuntimeException{
    public InvalidData(){super("Apparently the information provided is not valid!");}
}

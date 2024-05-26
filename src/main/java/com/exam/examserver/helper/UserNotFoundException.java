package com.exam.examserver.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User with these details is NOT FOUND !!!!");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}

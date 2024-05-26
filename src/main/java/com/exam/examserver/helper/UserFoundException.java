package com.exam.examserver.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("User with these details found in Database !! Try with another one");
    }
    public UserFoundException(String msg){
        super(msg);
    }
}

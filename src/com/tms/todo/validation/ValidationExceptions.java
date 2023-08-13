package com.tms.todo.validation;

public class ValidationExceptions extends Exception{
    public ValidationExceptions(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "ValidationException{"
                + "message =" + getMessage()
                + "}";
    }
}

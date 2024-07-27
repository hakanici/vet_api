package dev.patika.vet.core.exception;

public class NotFoundCustomerException extends RuntimeException{
    public NotFoundCustomerException(String message){
        super(message);
    }
}

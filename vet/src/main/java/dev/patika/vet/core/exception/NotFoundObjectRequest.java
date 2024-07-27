package dev.patika.vet.core.exception;

public class NotFoundObjectRequest extends RuntimeException{
    public NotFoundObjectRequest(String message){
        super(message);
    }
}

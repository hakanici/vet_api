package dev.patika.vet.core.exception;

public class NotFoundDoctorException extends RuntimeException{
    public NotFoundDoctorException(String message){
        super(message);
    }
}

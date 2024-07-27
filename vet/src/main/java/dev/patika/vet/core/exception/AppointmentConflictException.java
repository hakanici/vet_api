package dev.patika.vet.core.exception;

public class AppointmentConflictException extends RuntimeException{
    public AppointmentConflictException(String message){
        super(message);
    }
}

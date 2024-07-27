package dev.patika.vet.core.exception;

public class NotFoundAppointment extends RuntimeException{
    public NotFoundAppointment(String message){
        super(message);
    }
}

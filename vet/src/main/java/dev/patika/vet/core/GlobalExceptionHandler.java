package dev.patika.vet.core;

import dev.patika.vet.core.exception.*;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.Msg;
import dev.patika.vet.core.utilities.ResultHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.NullPointerException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handlerNotFoundException(NotFoundException e){
        return new ResponseEntity<>(ResultHelper.notFound(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){

        List<String> validaionErrorList = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());


        return new ResponseEntity<>(ResultHelper.validateError(validaionErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Result> handleDataIntegrityViolationException() {
        return new ResponseEntity<>(ResultHelper.NOT_UNIQUE(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> handleNotReadableException() {
        return new ResponseEntity<>(ResultHelper.UNREADABLE(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(java.lang.NullPointerException.class)
    public ResponseEntity<Result> handleNullPointerException() {
        return new ResponseEntity<>(ResultHelper.NULL_VALUES(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundCustomerException.class)
    public ResponseEntity<Result> handleNotFoundCustomerException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_CUSTOMER(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundObjectRequest.class)
    public ResponseEntity<Result> handleNotFoundObjectException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundAnimalException.class)
    public ResponseEntity<Result> handleNotFoundAnimal() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_ANIMAL(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateMismatchException.class)
    public ResponseEntity<Result> handleDateMismatchException() {
        return new ResponseEntity<>(ResultHelper.DATE_MISMATCH(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForUpdateNotFoundIdException.class)
    public ResponseEntity<Result> handeUpdateNotFoundId() {
        return new ResponseEntity<>(ResultHelper.UPDATE_NOT_FOUND_ID(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoneSenseInformationException.class)
    public ResponseEntity<Result> handleBadInformationException() {
        return new ResponseEntity<>(ResultHelper.BAD_DATE(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotUniqueValues.class)
    public ResponseEntity<Result> handleNotUniqueValuesException() {
        return new ResponseEntity<>(ResultHelper.NOT_UNIQUE(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundDoctorException.class)
    public ResponseEntity<Result> handleNotFoundDoctorException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_DOCTOR(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SameValuesException.class)
    public ResponseEntity<Result> handeSameValuesException() {
        return new ResponseEntity<>(ResultHelper.SAME_VALUES(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Result> resourceNotFoundException() {
        return new ResponseEntity<>(ResultHelper.RESOURCE_NOT_FOUND(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(DoctorDaysConflictException.class)
    public ResponseEntity<Result> handleDaysConflictException() {
        return new ResponseEntity<>(ResultHelper.DAYS_CONFLICT(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<Result> handeAppointmentConflictException() {
        return new ResponseEntity<>(ResultHelper.APPOINTMENT_CONFLICT(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundAppointment.class)
    public ResponseEntity<Result> handeNotFoundAppointmentException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_APPOINTMENT(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentAlreadyExists.class)
    public ResponseEntity<Result> handeAppointmentAlreadyExistException() {
        return new ResponseEntity<>(ResultHelper.EXIST_APPOINTMENT(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForceUpdateException.class)
    public ResponseEntity<Result> handleForceUpdateException() {
        return new ResponseEntity<>(ResultHelper.FORCE_UPDATE(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Result> handleMissingServletRequestParameterException() {
        return new ResponseEntity<>(ResultHelper.MISSING_PARAMETER(), HttpStatus.BAD_REQUEST);
    }


}

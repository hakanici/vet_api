package dev.patika.vet.core.utilities;

import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;

public class ResultHelper {

    public static <T>ResultData<T> created(T data){
        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    public static <T>ResultData<T> success(T data){
        return new ResultData<>(true, Msg.OK, "200", data);
    }

    public static <T> ResultData<T> validateError(T data){
        return new ResultData<>(false, Msg.VALIDATE_ERROR,"400", data);
    }

    public static Result notFound(String msg){
        return new Result(false, msg,"404");
    }

    public static Result successResult(){
        return new Result(true,Msg.OK,"200");
    }

    public static <T> ResultData<T> DELETE(T data) {
        return new ResultData<>(true, Msg.DELETED, "200", data);
    }

    public static <T> ResultData<T> ERROR(T data) {
        return new ResultData<>(false, Msg.NOT_NULL_MESSAGE, "409", data);
    }

    public static <T> ResultData<T> RESOLVE(T data) {
        return new ResultData<>(true, Msg.RESOLVE, "200", data);
    }

    public static Result NULL_POINTER() {
        return new Result(false, Msg.NULL_POINTER, "500");
    }

    public static Result NULL_VALUES() {
        return new Result(false, Msg.NULL_VALUES, "404");
    }

    public static Result NOT_FOUND_CUSTOMER() {
        return new Result(false, Msg.NOT_FOUND_CUSTOMER, "404");
    }

    public static Result NOT_FOUND_ANIMAL() {
        return new Result(false, Msg.NOT_FOUND_ANIMAL, "404");
    }

    public static Result DATE_MISMATCH() {
        return new Result(false, Msg.DATE_MISMATCH, "417");
    }

    public static Result UPDATE_NOT_FOUND_ID() {
        return new Result(false, Msg.UPDATE_NOT_FOUND_ID, "404");
    }

    public static Result BAD_DATE() {
        return new Result(false, Msg.BAD_DATE, "415");
    }

    public static Result NOT_UNIQUE() {
        return new Result(false, Msg.NOT_UNIQUE, "415");
    }

    public static Result NOT_FOUND_DOCTOR() {
        return new Result(false, Msg.NOT_FOUND_DOCTOR, "404");
    }

    public static Result SAME_VALUES() {
        return new Result(false, Msg.SAME_VALUES, "409");
    }

    public static Result RESOURCE_NOT_FOUND() {
        return new Result(false, Msg.RESOURCE_NOT_FOUND, "409");
    }

    public static Result DAYS_CONFLICT() {
        return new Result(false, Msg.DAYS_CONFLICT, "400");
    }

    public static Result APPOINTMENT_CONFLICT() {
        return new Result(false, Msg.APPOINTMENT_CONFLICT, "400");
    }

    public static Result NOT_FOUND_APPOINTMENT() {
        return new Result(false, Msg.NOT_FOUND_APPOINTMENT, "400");
    }

    public static Result EXIST_APPOINTMENT() {
        return new Result(false, Msg.EXISTING_APPOINTMENT, "400");
    }

    public static Result UNREADABLE() {
        return new Result(false, Msg.UNREADABLE, "502");
    }
    public static Result FORCE_UPDATE() {
        return new Result(false, Msg.FORCE_UPDATE, "502");
    }
    public static Result MISSING_PARAMETER() {
        return new Result(false, Msg.MISSING_PARAMETER, "502");
    }

    public static Result NOT_FOUND() {
        return new Result(false, Msg.NOT_FOUND, "404");
    }
}
